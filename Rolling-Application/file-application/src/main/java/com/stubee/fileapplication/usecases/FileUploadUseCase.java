package com.stubee.fileapplication.usecases;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadUseCase {

    FileResponse uploadFile(MultipartFile multipartFile);

    List<FileResponse> uploadFileList(List<MultipartFile> multipartFileList);

    void delete(String path);

}