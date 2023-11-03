package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.reviewapplication.mapper.StoryMapper;
import com.stubee.reviewapplication.usecases.command.ModifyStoryUseCase;
import com.stubee.rollingdomains.domain.story.model.CorporationDetails;
import com.stubee.rollingdomains.domain.story.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.story.model.ReviewGrades;
import com.stubee.rollingdomains.domain.story.services.ModifyStoryService;
import com.stubee.reviewapplication.usecases.command.ModifyStoryCommand;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyStoryApi implements ModifyStoryUseCase {

    private final ModifyStoryService modifyStoryService;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public void modify(final ModifyStoryCommand command) {
        final EmploymentDetails employmentDetails = StoryMapper.toEmploymentDetails(command);

        final CorporationDetails corporationDetails = StoryMapper.toCorporationDetails(command);

        final ReviewGrades reviewGrades = StoryMapper.toReviewGrades(command);

        modifyStoryService.modify(command.id(), employmentDetails, corporationDetails, reviewGrades,
                getCurrentMemberPort.getMemberId());
    }

}