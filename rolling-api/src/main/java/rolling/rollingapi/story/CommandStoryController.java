package rolling.rollingapi.story;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rolling.application.story.interactor.command.DeleteStoryCommand;
import rolling.application.story.interactor.command.DeleteStoryUseCase;
import rolling.application.story.interactor.command.ModifyStoryUseCase;
import rolling.application.story.interactor.command.RegisterStoryUseCase;
import rolling.domain.story.model.Story;
import rolling.domain.story.model.StoryId;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Command Story", description = "Command Story API")
@RestController
@RequestMapping(value = "/story")
@RequiredArgsConstructor
public class CommandStoryController {

    private final RegisterStoryUseCase registerStoryUseCase;
    private final DeleteStoryUseCase deleteStoryUseCase;
    private final ModifyStoryUseCase modifyStoryUseCase;

    @Operation(description = "Story 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Story register(final @RequestBody @Validated RegisterStoryRequest request) {
        return registerStoryUseCase.register(request.toCommand());
    }

    @Operation(description = "Story 삭제")
    @DeleteMapping("/{storyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable Long storyId) {
        deleteStoryUseCase.delete(new DeleteStoryCommand(StoryId.of(storyId)));
    }

    @Operation(description = "Story 수정")
    @PatchMapping("/{storyId}")
    @ResponseStatus(NO_CONTENT)
    public void modify(final @RequestBody @Validated ModifyStoryRequest request, @PathVariable Long storyId) {
        modifyStoryUseCase.modify(request.toCommand(storyId));
    }

}