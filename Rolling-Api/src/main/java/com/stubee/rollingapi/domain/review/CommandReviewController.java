package com.stubee.rollingapi.domain.review;

import com.stubee.rollingapi.domain.review.request.WriteReviewRequest;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingusecases.domain.review.commands.DeleteReviewCommand;
import com.stubee.rollingusecases.domain.review.usecases.command.DeleteReviewUseCase;
import com.stubee.rollingusecases.domain.review.usecases.command.WriteReviewUseCase;
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