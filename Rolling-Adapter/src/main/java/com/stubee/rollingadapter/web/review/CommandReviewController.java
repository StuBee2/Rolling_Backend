package com.stubee.rollingadapter.web.review;

import com.stubee.rollingadapter.web.review.request.WriteReviewRequest;
import com.stubee.rollingapplication.domain.review.port.api.command.DeleteReviewUseCase;
import com.stubee.rollingapplication.domain.review.port.api.command.WriteReviewUseCase;
import com.stubee.rollingcore.domain.review.command.DeleteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Review", description = "Command Review API")
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class CommandReviewController {

    private final DeleteReviewUseCase commandReviewUseCase;
    private final WriteReviewUseCase writeReviewUseCase;

    @Operation(description = "Review 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Review write(final @RequestBody @Validated WriteReviewRequest request) {
        return writeReviewUseCase.write(request.toCommand());
    }

    @Operation(description = "Review 삭제")
    @DeleteMapping("/{reviewId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable UUID reviewId) {
        commandReviewUseCase.delete(DeleteReviewCommand.toCommand(reviewId));
    }

}