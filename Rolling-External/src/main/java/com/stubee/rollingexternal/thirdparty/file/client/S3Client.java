package com.stubee.rollingexternal.thirdparty.file.client;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Client {

    String uploadFile(MultipartFile multipartFile);

    List<String> uploadFileList(List<MultipartFile> multipartFileList);

    void delete(String path);

}