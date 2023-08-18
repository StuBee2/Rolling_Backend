package com.stubee.reviewapplication.usecases.command;

import com.stubee.reviewapplication.commands.WriteReviewCommand;
import com.stubee.rollingdomains.domain.review.model.Review;

public interface WriteReviewUseCase {

    Review write(WriteReviewCommand command);

}