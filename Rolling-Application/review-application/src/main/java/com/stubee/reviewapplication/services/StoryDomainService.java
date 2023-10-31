package com.stubee.reviewapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.reviewapplication.outports.command.CommandStoryPort;
import com.stubee.reviewapplication.outports.query.QueryStoryByIdPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.exception.StoryNotFoundException;
import com.stubee.rollingdomains.domain.story.model.*;
import com.stubee.rollingdomains.domain.story.services.DeleteStoryService;
import com.stubee.rollingdomains.domain.story.services.ModifyStoryService;
import com.stubee.rollingdomains.domain.story.services.WriteStoryService;
import com.stubee.rollingdomains.domain.story.services.commands.DeleteStoryCommand;
import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;
import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class StoryDomainService implements WriteStoryService, DeleteStoryService, ModifyStoryService {

    private final CommandStoryPort commandStoryPort;
    private final QueryStoryByIdPort queryStoryByIdPort;

    @Override
    public Story write(final RegisterStoryCommand command, final MemberId memberId) {
        return commandStoryPort.save(command.toDomain(memberId));
    }

    @Override
    public void delete(final DeleteStoryCommand command, final MemberId memberId) {
        final Story story = this.getById(command.storyId());

        story.isAuthor(memberId);

        commandStoryPort.deleteById(command.storyId());
    }

    @Override
    public void modify(final ModifyStoryCommand command, final MemberId memberId) {
        final Story story = this.getById(command.id());

        story.isAuthor(memberId);

        commandStoryPort.update(story.update(toEmploymentDetails(command), toCorporationDetails(command),
                toReviewGrades(command)));
    }

    private Story getById(final StoryId storyId) {
        return queryStoryByIdPort.findById(storyId.getId())
                .orElseThrow(() -> StoryNotFoundException.EXCEPTION);
    }

    private EmploymentDetails toEmploymentDetails(final ModifyStoryCommand command) {
        return EmploymentDetails.builder()
                .schoolLife(command.schoolLife())
                .preparationCourse(command.preparationCourse())
                .employmentProcess(command.employmentProcess())
                .interviewQuestion(command.interviewQuestion())
                .mostImportantThing(command.mostImportantThing())
                .build();
    }

    private CorporationDetails toCorporationDetails(final ModifyStoryCommand command) {
        return CorporationDetails.builder()
                .position(command.position())
                .welfare(command.welfare())
                .commuteTime(command.commuteTime())
                .meal(command.meal())
                .pros(command.pros())
                .cons(command.cons())
                .etc(command.etc())
                .build();
    }

    private ReviewGrades toReviewGrades(final ModifyStoryCommand command) {
        return ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(Double.valueOf(command.salaryAndBenefits()))
                .workLifeBalance(Double.valueOf(command.workLifeBalance()))
                .organizationalCulture(Double.valueOf(command.organizationalCulture()))
                .careerAdvancement(Double.valueOf(command.careerAdvancement()))
                .build();
    }

}