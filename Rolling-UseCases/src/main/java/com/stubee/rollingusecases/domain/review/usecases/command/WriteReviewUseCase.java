package com.stubee.rollingusecases.domain.review.usecases.command;

import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingusecases.domain.review.commands.WriteReviewCommand;

public interface WriteReviewUseCase {

    Review write(WriteReviewCommand command);

}