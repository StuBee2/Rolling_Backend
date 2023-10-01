package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public record MemberDetails(
        String nickName,
        MemberRole memberRole,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    @Builder(builderClassName = "OnlyWithRoleBuilder", builderMethodName = "OnlyWithRoleBuilder")
    public MemberDetails(MemberRole memberRole) {
        this(null, memberRole, null, null);
    }

    @Builder(builderClassName = "AllArgsBuilder", builderMethodName = "AllArgsBuilder")
    public MemberDetails {
        Objects.requireNonNull(memberRole, "MemberRole can not be null");
    }

    public MemberDetails updateNickName(final String nickName) {
        return new MemberDetails(nickName, memberRole, createdAt, modifiedAt);
    }

    public MemberDetails elevateToMember() {
        return new MemberDetails(nickName, MemberRole.MEMBER, createdAt, modifiedAt);
    }
}