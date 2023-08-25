package com.stubee.reviewpersistence.repository;

import com.stubee.persistencecommons.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandReviewJpaRepository extends CrudRepository<ReviewEntity, UUID> {
}