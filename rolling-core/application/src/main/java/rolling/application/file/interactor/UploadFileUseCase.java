package rolling.application.file.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import rolling.application.file.outport.UploadFilePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadFileUseCase {

    private final UploadFilePort uploadFilePort;

    public FileResponse upload(final MultipartFile multipartFile) {
        return uploadFilePort.upload(multipartFile);
    }

    public List<FileResponse> upload(final List<MultipartFile> multipartFileList) {
        return uploadFilePort.uploadList(multipartFileList);
    }

    public void delete(final String path) {
        uploadFilePort.delete(path);
    }

}