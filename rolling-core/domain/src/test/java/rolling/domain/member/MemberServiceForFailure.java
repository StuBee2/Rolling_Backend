package rolling.domain.member;

import rolling.domain.member.service.MemberService;

public class MemberServiceForFailure implements MemberService {

    @Override
    public boolean isNicknameDuplicate(final String nickname) {
        return true;
    }

}