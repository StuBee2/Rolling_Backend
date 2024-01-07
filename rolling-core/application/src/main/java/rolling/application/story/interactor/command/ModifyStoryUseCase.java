package rolling.application.story.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.story.outport.CommandStoryPort;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.story.model.CorporationDetails;
import rolling.domain.story.model.EmploymentDetails;
import rolling.domain.story.model.ReviewGrades;
import rolling.domain.story.model.Story;

@Component
@Transactional
@RequiredArgsConstructor
public class ModifyStoryUseCase {

    private final CommandStoryPort commandStoryPort;
    private final QueryStoryPort queryStoryPort;
    private final MemberSessionPort memberSessionPort;

    public void modify(final ModifyStoryCommand command) {
        final EmploymentDetails employmentDetails = StoryMapper.toEmploymentDetails(command);
        final CorporationDetails corporationDetails = StoryMapper.toCorporationDetails(command);
        final ReviewGrades reviewGrades = StoryMapper.toReviewGrades(command);
        final Story story = queryStoryPort.getBy(command.id().getId());

        story.modify(employmentDetails, corporationDetails, reviewGrades, memberSessionPort.currentId());

        commandStoryPort.save(story);
    }

}