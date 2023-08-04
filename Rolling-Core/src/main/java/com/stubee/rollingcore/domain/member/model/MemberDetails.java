package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.MemberRole;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record MemberDetails(
        String nickName,
        MemberRole memberRole,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static MemberDetails create(final String nickName, final MemberRole memberRole,
                                       final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        return MemberDetails.builder()
                .nickName(nickName)
                .memberRole(memberRole)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

    public static MemberDetails createOnlyMemberRole(final MemberRole memberRole) {
        return MemberDetails.builder()
                .memberRole(memberRole)
                .build();
    }

    public MemberDetails updateNickName(final String nickName) {
        return MemberDetails.builder()
                .nickName(nickName)
                .memberRole(memberRole)
                .createdAt(createdAt)
                .build();
    }

    public MemberDetails elevateToMember() {
        return MemberDetails.builder()
                .nickName(nickName)
                .memberRole(MemberRole.MEMBER)
                .createdAt(createdAt)
                .build();
    }
}