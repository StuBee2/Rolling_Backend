package com.stubee.reviewpersistence.adapters;

import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.reviewapplication.outports.command.CommandStoryPort;
import com.stubee.reviewpersistence.mapper.StoryMapper;
import com.stubee.reviewpersistence.repository.CommandStoryJpaRepository;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.rollingdomains.domain.story.model.StoryId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandStoryAdapter implements CommandStoryPort {

    private final CommandStoryJpaRepository commandReviewJpaRepository;
    private final StoryMapper reviewMapper;

    @Override
    public Story register(final Story story) {
        return reviewMapper.toDomain(commandReviewJpaRepository.save(reviewMapper.toEntity(story)));
    }

    @Override
    public void deleteById(final StoryId storyId) {
        commandReviewJpaRepository.deleteById(storyId.getId());
    }

}