package rolling.domain.member;

import rolling.domain.member.service.MemberService;

public class MemberServiceForException implements MemberService {

    @Override
    public boolean isNicknameDuplicate(final String nickname) {
        return true;
    }

}