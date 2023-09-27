package com.stubee.reviewapplication.outports.query;

import com.stubee.reviewapplication.usecases.query.response.ReviewStatusResponse;

import java.util.UUID;

public interface QueryReviewStatusPort {

    ReviewStatusResponse findByMemberId(UUID memberId);

}