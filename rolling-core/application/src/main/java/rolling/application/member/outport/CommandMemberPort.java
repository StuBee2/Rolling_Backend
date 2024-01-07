package rolling.application.member.outport;

import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberProfile;

public interface CommandMemberPort {

    Member saveWithId(Member member);

    Member saveExceptId(Member member);

    Member saveOrUpdate(MemberProfile memberProfile);

}