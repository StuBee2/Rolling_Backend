package rolling.application.member.outport;

import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberProfile;

public interface CommandMemberPort {

    Member save(Member member);

    Member save(MemberProfile memberProfile);

}