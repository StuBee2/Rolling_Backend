package com.stubee.rollingapi.domain.review;

import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;
import com.stubee.reviewapplication.usecases.command.DeleteReviewUseCase;
import com.stubee.reviewapplication.usecases.command.RegisterReviewUseCase;
import com.stubee.rollingapi.domain.review.request.RegisterReviewRequest;
import com.stubee.rollingdomains.domain.review.model.Review;
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

    private final DeleteReviewUseCase deleteReviewUseCase;
    private final RegisterReviewUseCase registerReviewUseCase;

    @Operation(description = "Review 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Review register(final @RequestBody @Validated RegisterReviewRequest request) {
        return registerReviewUseCase.register(request.toCommand());
    }

    @Operation(description = "Review 삭제")
    @DeleteMapping("/{reviewId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable UUID reviewId) {
        deleteReviewUseCase.delete(DeleteReviewCommand.toCommand(reviewId));
    }

}