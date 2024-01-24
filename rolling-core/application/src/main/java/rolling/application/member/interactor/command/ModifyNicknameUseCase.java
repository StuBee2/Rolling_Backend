package rolling.application.member.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.CommandMemberPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.member.model.Member;
import rolling.domain.member.service.MemberService;

@Component
@Transactional
@RequiredArgsConstructor
public class ModifyNicknameUseCase {

    private final MemberSessionPort memberSessionPort;
    private final CommandMemberPort commandMemberPort;
    private final MemberService memberService;

    public void modify(final ModifyNicknameCommand command) {
        final Member member = memberSessionPort.current();

        member.modify(command.nickname(), memberService);

        commandMemberPort.save(member);
    }

}