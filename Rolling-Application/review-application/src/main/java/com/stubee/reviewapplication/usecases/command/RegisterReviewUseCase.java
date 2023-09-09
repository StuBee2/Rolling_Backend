package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;
import com.stubee.rollingdomains.domain.review.model.Review;

public interface RegisterReviewUseCase {

    Review register(RegisterReviewCommand command);

}