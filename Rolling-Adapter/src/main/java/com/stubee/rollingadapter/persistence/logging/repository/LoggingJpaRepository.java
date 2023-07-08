package com.stubee.rollingadapter.persistence.logging.repository;

import com.stubee.rollingadapter.persistence.logging.entity.LoggingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoggingJpaRepository extends JpaRepository<LoggingEntity, Long> {

    List<LoggingEntity> findAllByMemberId(UUID memberId);

}