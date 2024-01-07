package rolling.application.story.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.story.outport.CommandStoryPort;
import rolling.domain.company.exception.CompanyNotFoundException;
import rolling.domain.member.model.MemberId;
import rolling.domain.story.model.Story;

@Component
@Transactional
@RequiredArgsConstructor
public class RegisterStoryUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final CommandStoryPort commandStoryPort;
    private final MemberSessionPort memberSessionPort;

    public Story register(final RegisterStoryCommand command) {
        if(!queryCompanyPort.existsBy(command.companyId())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        final MemberId memberId = memberSessionPort.currentId();
        final Story story = StoryMapper.toDomain(command, memberId);

        return commandStoryPort.save(story);
    }

}