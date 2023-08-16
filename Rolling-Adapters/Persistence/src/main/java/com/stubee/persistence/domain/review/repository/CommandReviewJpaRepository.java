package com.stubee.persistence.domain.review.repository;

import com.stubee.persistence.domain.review.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandReviewJpaRepository extends CrudRepository<ReviewEntity, UUID> {
}