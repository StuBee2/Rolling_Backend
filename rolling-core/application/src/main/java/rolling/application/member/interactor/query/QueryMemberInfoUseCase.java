package rolling.application.member.interactor.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.MemberSessionPort;
import rolling.application.member.outport.QueryMemberPort;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryMemberInfoUseCase {

    private final MemberSessionPort memberSessionPort;
    private final QueryMemberPort queryMemberPort;

    public MemberResponse query() {
        return MemberResponse.of(memberSessionPort.current());
    }

    public MemberResponse query(final Long memberId) {
        return MemberResponse.of(queryMemberPort.getBy(memberId));
    }

}