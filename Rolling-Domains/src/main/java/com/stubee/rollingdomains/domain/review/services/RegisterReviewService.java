package com.stubee.rollingdomains.domain.review.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.services.commands.RegisterReviewCommand;

public interface RegisterReviewService {

    Review register(RegisterReviewCommand command, MemberId memberId);

}