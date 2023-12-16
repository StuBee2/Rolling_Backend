package com.stubee.fileapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.ExternalService;
import com.stubee.fileapplication.outports.UploadImagePort;
import com.stubee.fileapplication.usecases.FileUploadUseCase;
import com.stubee.fileapplication.usecases.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ExternalService
@RequiredArgsConstructor
class FileUploadApi implements FileUploadUseCase {

    private final UploadImagePort uploadImagePort;

    @Override
    public FileResponse uploadFile(final MultipartFile multipartFile) {
        return uploadImagePort.upload(multipartFile);
    }

    @Override
    public List<FileResponse> uploadFileList(final List<MultipartFile> multipartFileList) {
        return uploadImagePort.uploadList(multipartFileList);
    }

    @Override
    public void delete(final String path) {
        uploadImagePort.delete(path);
    }

}