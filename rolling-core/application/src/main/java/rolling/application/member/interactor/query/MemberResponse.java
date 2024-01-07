package rolling.application.member.interactor.query;

import rolling.domain.common.model.TSID;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.SocialDetails;

public record MemberResponse(
        TSID memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(TSID.of(member.memberId()), member.socialDetails(), member.memberDetails());
    }
}