package com.stubee.rollingapplication.domain.review.port.spi;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryReviewPort {

    Optional<ReviewInfoResponse> findById(UUID id);

    List<ReviewQueryResponse> findByMemberId(UUID memberId, PageRequest pageRequest);

    List<ReviewInfoResponse> findByCompanyId(UUID companyId, PageRequest pageRequest);

}