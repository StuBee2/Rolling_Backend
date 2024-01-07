package rolling.application.member.outport;

import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberId;

public interface MemberSessionPort {

    Member current();

    MemberId currentId();

}