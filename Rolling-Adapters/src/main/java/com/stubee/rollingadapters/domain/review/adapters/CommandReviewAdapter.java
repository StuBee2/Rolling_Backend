package com.stubee.rollingadapters.domain.review.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.model.ReviewId;
import com.stubee.rollingexternal.persistence.domain.review.mapper.ReviewMapper;
import com.stubee.rollingexternal.persistence.domain.review.repository.CommandReviewJpaRepository;
import com.stubee.rollingports.domain.review.ports.CommandReviewPort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandReviewAdapter implements CommandReviewPort {

    private final CommandReviewJpaRepository commandReviewJpaRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review save(final Review review) {
        return reviewMapper.toDomain(commandReviewJpaRepository.save(reviewMapper.toEntity(review)));
    }

    @Override
    public void deleteById(final ReviewId reviewId) {
        commandReviewJpaRepository.deleteById(reviewId.id());
    }

}