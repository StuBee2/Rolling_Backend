package rolling.rollingapi.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rolling.application.file.interactor.FileResponse;
import rolling.application.file.interactor.UploadFileUseCase;

@Tag(name = "File", description = "File API")
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final UploadFileUseCase uploadFileUseCase;

    @Operation(description = "file 업로드")
    @PostMapping
    public FileResponse upload(final @RequestPart("file") MultipartFile file) {
        return uploadFileUseCase.upload(file);
    }

}