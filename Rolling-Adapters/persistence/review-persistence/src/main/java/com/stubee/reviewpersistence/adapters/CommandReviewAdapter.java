package com.stubee.reviewpersistence.adapters;

import com.stubee.persistencecommons.commons.annotations.Adapter;
import com.stubee.reviewapplication.outports.CommandReviewPort;
import com.stubee.reviewpersistence.mapper.ReviewMapper;
import com.stubee.reviewpersistence.repository.CommandReviewJpaRepository;
import com.stubee.rollingdomains.domain.review.model.Review;
import com.stubee.rollingdomains.domain.review.model.ReviewId;
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
        commandReviewJpaRepository.deleteById(reviewId.getId());
    }

}