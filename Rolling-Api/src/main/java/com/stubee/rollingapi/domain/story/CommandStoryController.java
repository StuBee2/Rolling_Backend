package com.stubee.rollingapi.domain.story;

import com.stubee.rollingdomains.domain.story.services.commands.DeleteStoryCommand;
import com.stubee.reviewapplication.usecases.command.DeleteStoryUseCase;
import com.stubee.reviewapplication.usecases.command.RegisterStoryUseCase;
import com.stubee.rollingapi.domain.story.request.RegisterStoryRequest;
import com.stubee.rollingdomains.domain.story.model.Story;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Story", description = "Command Story API")
@RestController
@RequestMapping(value = "/story")
@RequiredArgsConstructor
public class CommandStoryController {

    private final RegisterStoryUseCase registerStoryUseCase;
    private final DeleteStoryUseCase deleteStoryUseCase;

    @Operation(description = "Review 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Story register(final @RequestBody @Validated RegisterStoryRequest request) {
        return registerStoryUseCase.register(request.toCommand());
    }

    @Operation(description = "Review 삭제")
    @DeleteMapping("/{reviewId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable Long reviewId) {
        deleteStoryUseCase.delete(DeleteStoryCommand.toCommand(reviewId));
    }

}