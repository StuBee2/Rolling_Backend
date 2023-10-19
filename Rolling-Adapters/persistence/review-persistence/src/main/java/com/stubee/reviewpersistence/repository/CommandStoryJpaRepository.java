package com.stubee.reviewpersistence.repository;

import com.stubee.persistencecommons.entity.StoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandStoryJpaRepository extends CrudRepository<StoryEntity, Long> {
}