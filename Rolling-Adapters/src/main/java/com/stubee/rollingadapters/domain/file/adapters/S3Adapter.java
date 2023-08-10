package com.stubee.rollingadapters.domain.file.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingexternal.thirdparty.file.client.S3Client;
import com.stubee.rollingports.domain.file.ports.S3Port;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Adapter
@RequiredArgsConstructor
public class S3Adapter implements S3Port {

    private final S3Client s3Client;

    @Override
    public String uploadFile(final MultipartFile multipartFile) {
        return s3Client.uploadFile(multipartFile);
    }

    @Override
    public List<String> uploadFileList(final List<MultipartFile> multipartFileList) {
        return s3Client.uploadFileList(multipartFileList);
    }

    @Override
    public void delete(final String path) {
        s3Client.delete(path);
    }

}