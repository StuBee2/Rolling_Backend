package com.stubee.reviewpersistence.adapters;

import com.stubee.persistencecommons.entity.StoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommandStoryJpaRepository extends CrudRepository<StoryEntity, Long> {
}