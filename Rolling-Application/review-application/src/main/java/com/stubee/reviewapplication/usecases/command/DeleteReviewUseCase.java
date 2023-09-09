package com.stubee.reviewapplication.usecases.command;

import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;

public interface DeleteReviewUseCase {

    void delete(DeleteReviewCommand command);

}