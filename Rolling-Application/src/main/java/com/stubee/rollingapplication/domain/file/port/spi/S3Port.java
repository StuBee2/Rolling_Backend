package com.stubee.rollingapplication.domain.file.port.spi;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Port {

    String uploadFile(MultipartFile multipartFile);

    List<String> uploadFileList(List<MultipartFile> multipartFileList);

    void delete(String path);

}