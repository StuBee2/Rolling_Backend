package com.stubee.fileapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.ExternalService;
import com.stubee.fileapplication.outports.S3Port;
import com.stubee.fileapplication.usecases.FileUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ExternalService
@RequiredArgsConstructor
public class FileUploadApi implements FileUploadUseCase {

    private final S3Port s3Port;

    @Override
    public String uploadFile(final MultipartFile multipartFile) {
        return s3Port.uploadFile(multipartFile);
    }

    @Override
    public List<String> uploadFileList(final List<MultipartFile> multipartFileList) {
        return s3Port.uploadFileList(multipartFileList);
    }

    @Override
    public void delete(final String path) {
        s3Port.delete(path);
    }

}