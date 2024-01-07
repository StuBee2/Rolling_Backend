package rolling.application.member.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rolling.application.member.outport.QueryMemberPort;
import rolling.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
class MemberServiceImpl implements MemberService {

    private final QueryMemberPort queryMemberPort;

    public boolean isNicknameDuplicate(final String nickname) {
        return queryMemberPort.existsBy(nickname);
    }

}