package com.stubee.rollingadapter.persistence.review.adapter;

import com.stubee.rollingapplication.common.annotation.Adapter;
import com.stubee.rollingadapter.persistence.review.mapper.ReviewMapper;
import com.stubee.rollingadapter.persistence.review.repository.ReviewJpaRepository;
import com.stubee.rollingapplication.domain.review.port.spi.CommandReviewPort;
import com.stubee.rollingcore.domain.review.model.Review;
import com.stubee.rollingcore.domain.review.model.ReviewId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandReviewAdapter implements CommandReviewPort {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review save(Review review) {
        return reviewMapper.toDomain(reviewJpaRepository.save(reviewMapper.toEntity(review)));
    }

    @Override
    public void deleteById(ReviewId reviewId) {
        reviewJpaRepository.deleteById(reviewId.id());
    }

}