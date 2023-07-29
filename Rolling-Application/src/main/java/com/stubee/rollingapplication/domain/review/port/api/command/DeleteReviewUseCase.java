package com.stubee.rollingapplication.domain.review.port.api.command;

import com.stubee.rollingcore.domain.review.command.DeleteReviewCommand;

public interface DeleteReviewUseCase {

    void delete(DeleteReviewCommand command);

}