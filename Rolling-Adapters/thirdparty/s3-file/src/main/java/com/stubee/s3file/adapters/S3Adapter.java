package com.stubee.s3file.adapters;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.stubee.fileapplication.outports.S3Port;
import com.stubee.s3file.exception.FileUploadException;
import com.stubee.s3file.properties.S3Properties;
import com.stubee.thirdpartycommons.annotations.Adapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Adapter
@Slf4j
@RequiredArgsConstructor
public class S3Adapter implements S3Port {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    public String uploadFile(final MultipartFile multipartFile) {
        final String fileName = getFileName(multipartFile.getName());

        amazonS3.putObject(getRequest(multipartFile, fileName));

        return getUrl(fileName);
    }

    @Override
    public List<String> uploadFileList(final List<MultipartFile> multipartFileList) {
        return multipartFileList.stream().map(this::uploadFile).toList();
    }

    @Override
    public void delete(final String path) {
        amazonS3.deleteObject(s3Properties.getBucket(), path);
    }

    private PutObjectRequest getRequest(final MultipartFile multipartFile, final String fileName) {
        try {
            return new PutObjectRequest(s3Properties.getBucket(), fileName, multipartFile.getInputStream(),
                    getMetadata(multipartFile)).withCannedAcl(CannedAccessControlList.PublicRead);
        } catch (IOException e) {
            throw FileUploadException.EXCEPTION;
        }
    }

    private String getFileName(final String fileName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + fileName;
    }

    private String getUrl(final String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private ObjectMetadata getMetadata(final MultipartFile multipartFile) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        return objectMetadata;
    }

}