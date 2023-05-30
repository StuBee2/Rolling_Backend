package com.stubee.rollingadapter.out.persistence.review.repository;

import com.stubee.rollingadapter.out.persistence.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, UUID> {
}