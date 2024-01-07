package rolling.application.file.outport;

import org.springframework.web.multipart.MultipartFile;
import rolling.application.file.interactor.FileResponse;

import java.util.List;

public interface UploadFilePort {

    FileResponse upload(MultipartFile multipartFile);

    List<FileResponse> uploadList(List<MultipartFile> multipartFileList);

    void delete(String path);

}