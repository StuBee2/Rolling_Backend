package com.stubee.rollingapplication.domain.review.port.api;

import com.stubee.rollingcore.domain.review.dto.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;

public interface CommandReviewUseCase {

    Review write(WriteReviewCommand command);

}