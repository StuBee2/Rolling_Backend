package com.stubee.fileapplication.usecases;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadUseCase {

    String uploadFile(MultipartFile multipartFile);

    List<String> uploadFileList(List<MultipartFile> multipartFileList);

    void delete(String path);

}