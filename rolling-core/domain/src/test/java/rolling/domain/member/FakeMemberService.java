package rolling.domain.member;

import rolling.domain.member.service.MemberService;

public class FakeMemberService implements MemberService {

    @Override
    public boolean isNicknameDuplicate(String nickname) {
        return true;
    }

}