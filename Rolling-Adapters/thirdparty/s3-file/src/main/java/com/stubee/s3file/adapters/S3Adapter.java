package com.stubee.s3file.adapters;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.fileapplication.outports.UploadImagePort;
import com.stubee.fileapplication.usecases.FileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Adapter
@Slf4j
@RequiredArgsConstructor
class S3Adapter implements UploadImagePort {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    public FileResponse upload(final MultipartFile multipartFile) {
        final String fileName = getFileName(multipartFile.getName());

        PutObjectRequest request = getRequest(multipartFile, fileName);

        amazonS3.putObject(request);

        return FileResponse.of(getUrl(fileName), getRGB(multipartFile));
    }

    private String getFileName(final String fileName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + fileName;
    }

    private String getUrl(final String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    @Override
    public List<FileResponse> uploadList(final List<MultipartFile> multipartFileList) {
        return multipartFileList.stream().map(this::upload).toList();
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

    private Integer getRGB(final MultipartFile multipartFile) {
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

            return bufferedImage.getRGB(1, 1);
        } catch (IOException e) {
            throw FileUploadException.EXCEPTION;
        }
    }

    private ObjectMetadata getMetadata(final MultipartFile multipartFile) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        return objectMetadata;
    }

}