package com.stubee.persistence.domain.review.adapters;

import com.stubee.persistence.domain.review.mapper.ReviewMapper;
import com.stubee.persistence.domain.review.repository.QueryReviewRepository;
import com.stubee.persistence.common.annotations.Adapter;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.response.ReviewInfoResponse;
import com.stubee.rollingdomains.domain.review.response.ReviewQueryResponse;
import com.stubee.rollingports.domain.review.ports.QueryReviewPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class QueryReviewAdapter implements QueryReviewPort {

    private final QueryReviewRepository queryReviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Optional<Review> findById(final UUID reviewId) {
        return Optional.ofNullable(reviewMapper.toDomain(queryReviewRepository.findById(reviewId)));
    }

    @Override
    public Optional<ReviewInfoResponse> findInfoById(final UUID reviewId) {
        return Optional.ofNullable(queryReviewRepository.findInfoById(reviewId));
    }

    @Override
    public List<ReviewQueryResponse> findByMemberId(final UUID memberId, PageRequest pageRequest) {
        return queryReviewRepository.findByMemberId(memberId, pageRequest);
    }

    @Override
    public List<ReviewInfoResponse> findByCompanyId(final UUID companyId, PageRequest pageRequest) {
        return queryReviewRepository.findByCompanyId(companyId, pageRequest);
    }

}