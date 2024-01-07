package rolling.domain.member.model;

import rolling.domain.member.consts.MemberRole;

import java.time.LocalDateTime;

public record MemberDetails(
        String nickName,
        MemberRole memberRole,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
}