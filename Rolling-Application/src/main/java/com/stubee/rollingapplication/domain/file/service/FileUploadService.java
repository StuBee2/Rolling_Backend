package com.stubee.rollingapplication.domain.file.service;

import com.stubee.rollingapplication.domain.file.port.spi.S3Port;
import com.stubee.rollingapplication.domain.file.port.api.FileUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUseCase {

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