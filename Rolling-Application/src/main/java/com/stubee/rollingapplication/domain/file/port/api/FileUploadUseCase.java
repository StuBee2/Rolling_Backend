package com.stubee.rollingapplication.domain.file.port.api;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadUseCase {

    String uploadFile(MultipartFile multipartFile);

    List<String> uploadFileList(List<MultipartFile> multipartFileList);

    void delete(String path);

}