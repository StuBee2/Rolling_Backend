package com.stubee.rollingdomains.domain.review.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.services.commands.DeleteReviewCommand;

public interface DeleteReviewService {

    void delete(DeleteReviewCommand command, MemberId memberId);

}