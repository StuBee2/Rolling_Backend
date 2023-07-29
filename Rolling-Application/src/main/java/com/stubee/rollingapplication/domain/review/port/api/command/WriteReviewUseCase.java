package com.stubee.rollingapplication.domain.review.port.api.command;

import com.stubee.rollingcore.domain.review.command.WriteReviewCommand;
import com.stubee.rollingcore.domain.review.model.Review;

public interface WriteReviewUseCase {

    Review write(WriteReviewCommand command);

}