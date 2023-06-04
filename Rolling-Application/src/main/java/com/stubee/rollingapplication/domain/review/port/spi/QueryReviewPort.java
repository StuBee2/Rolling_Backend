package com.stubee.rollingapplication.domain.review.port.spi;

import com.stubee.rollingcore.common.dto.PageDto;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.review.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryReviewPort {

    Optional<ReviewInfoResponse> findById(UUID id);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageDto pageDto);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageDto pageDto);

    List<Review> findAll();

}