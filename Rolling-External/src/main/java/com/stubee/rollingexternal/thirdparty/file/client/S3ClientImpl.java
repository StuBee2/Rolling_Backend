package com.stubee.rollingexternal.thirdparty.file.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.stubee.rollingexternal.global.exception.file.FileConvertException;
import com.stubee.rollingexternal.global.exception.file.FileUploadException;
import com.stubee.rollingexternal.thirdparty.file.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3ClientImpl implements S3Client {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    public String uploadFile(final MultipartFile multipartFile) {
        try {
            final File convertFile = convert(multipartFile)
                    .orElseThrow(() -> FileConvertException.EXCEPTION);

            final String fileName = getFileName(convertFile.getName());

            amazonS3.putObject(putRequest(fileName, convertFile));

            convertFile.delete();

            return getUrl(fileName);
        } catch (IOException e) {
            throw FileUploadException.EXCEPTION;
        }
    }

    @Override
    public List<String> uploadFileList(final List<MultipartFile> multipartFileList) {
        return multipartFileList.stream().map(this::uploadFile).toList();
    }

    @Override
    public void delete(final String path) {
        amazonS3.deleteObject(s3Properties.getBucket(), path);
    }

    private Optional<File> convert(final MultipartFile file) throws IOException {
        final File convertFile = new File(System.getProperty("user.home") + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String getFileName(final String convertFileName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + convertFileName;
    }

    private PutObjectRequest putRequest(final String fileName, final File convertFile) {
        return new PutObjectRequest(s3Properties.getBucket(), fileName, convertFile)
                .withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private String getUrl(final String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

}