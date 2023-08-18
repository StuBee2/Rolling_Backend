package com.stubee.reviewapplication.usecases.command;

import com.stubee.reviewapplication.commands.DeleteReviewCommand;

public interface DeleteReviewUseCase {

    void delete(DeleteReviewCommand command);

}