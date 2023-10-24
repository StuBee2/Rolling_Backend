package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import lombok.Builder;

import java.time.LocalDateTime;

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
        Assert.notNull(memberRole, "MemberRole must not be null");
    }

    MemberDetails updateNickName(final String nickName) {
        return new MemberDetails(nickName, memberRole, createdAt, modifiedAt);
    }

    MemberDetails elevateToMember() {
        if(memberRole!=MemberRole.TEMP) {
            return this;
        }
        return new MemberDetails(nickName, MemberRole.MEMBER, createdAt, modifiedAt);
    }
}