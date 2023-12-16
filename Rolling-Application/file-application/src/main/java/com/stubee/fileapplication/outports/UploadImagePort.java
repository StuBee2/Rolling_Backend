package com.stubee.fileapplication.outports;

import com.stubee.fileapplication.usecases.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadImagePort {

    FileResponse upload(MultipartFile multipartFile);

    List<FileResponse> uploadList(List<MultipartFile> multipartFileList);

    void delete(String path);

}