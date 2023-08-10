package com.stubee.rollingapi.domain.file;

import com.stubee.rollingusecases.domain.file.usecases.FileUploadUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File", description = "File API")
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadUseCase fileUploadUseCase;

    @Operation(description = "file 업로드")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(final @RequestPart("file") MultipartFile file) {
        return fileUploadUseCase.uploadFile(file);
    }

}