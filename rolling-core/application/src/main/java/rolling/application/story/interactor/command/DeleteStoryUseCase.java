package rolling.application.story.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.story.outport.CommandStoryPort;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.member.model.MemberId;
import rolling.domain.story.model.Story;

@Component
@Transactional
@RequiredArgsConstructor
public class DeleteStoryUseCase {

    private final CommandStoryPort commandStoryPort;
    private final QueryStoryPort queryStoryPort;
    private final MemberSessionPort memberSessionPort;

    public void delete(final DeleteStoryCommand command) {
        final MemberId memberId = memberSessionPort.currentId();
        final Story story = queryStoryPort.getBy(command.storyId().getId());

        story.isAuthor(memberId);

        commandStoryPort.deleteById(story.id());
    }

}