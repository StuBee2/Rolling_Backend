package com.stubee.rollingusecases.domain.review.usecases.command;

import com.stubee.rollingusecases.domain.review.commands.DeleteReviewCommand;

public interface DeleteReviewUseCase {

    void delete(DeleteReviewCommand command);

}