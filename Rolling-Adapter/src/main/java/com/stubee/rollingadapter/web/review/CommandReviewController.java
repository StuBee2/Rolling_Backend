package com.stubee.rollingadapter.web.review;

import com.stubee.rollingadapter.web.review.request.WriteReviewRequest;
import com.stubee.rollingapplication.domain.review.port.api.CommandReviewUseCase;
import com.stubee.rollingcore.domain.review.model.Review;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Review", description = "Command Review API")
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class CommandReviewController {

    private final CommandReviewUseCase commandReviewUseCase;

    @Operation(description = "Review 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Review write(@RequestBody @Validated WriteReviewRequest request) {
        return commandReviewUseCase.write(request.toCommand());
    }

}