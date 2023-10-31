package com.stubee.reviewpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.reviewapplication.outports.command.CommandStoryPort;
import com.stubee.reviewpersistence.mapper.StoryMapper;
import com.stubee.reviewpersistence.repository.CommandStoryJpaRepository;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.rollingdomains.domain.story.model.StoryId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandStoryAdapter implements CommandStoryPort {

    private final CommandStoryJpaRepository commandStoryJpaRepository;
    private final StoryMapper storyMapper;

    @Override
    public Story save(final Story story) {
        return storyMapper.toDomain(commandStoryJpaRepository.save(storyMapper.toEntity(story)));
    }

    @Override
    public void deleteById(final StoryId storyId) {
        commandStoryJpaRepository.deleteById(storyId.getId());
    }

    @Override
    public void update(final Story story) {
        commandStoryJpaRepository.save(storyMapper.toEntityWithId(story));
    }

}