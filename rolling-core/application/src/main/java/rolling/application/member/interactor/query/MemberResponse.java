package rolling.application.member.interactor.query;

import rolling.domain.common.model.TSID;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.SocialDetails;

import java.time.LocalDateTime;

public record MemberResponse(
        TSID memberId,
        MemberRole role,
        MemberDetails details,
        SocialDetails socialDetails,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(
                TSID.of(member.id()),
                member.role(),
                member.details(),
                member.socialDetails(),
                member.createdAt(),
                member.modifiedAt()
        );
    }
}