package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;

public record MemberProfile (
        Integer socialId,
        String socialLoginId,
        String name,
        String email,
        String imageUrl,
        MemberRole memberRole,
        LoginType loginType) {
    public Member toMember() {
        return Member.createExceptId(SocialDetails.create(socialId, socialLoginId, loginType, name, email, imageUrl),
                MemberDetails.createOnlyMemberRole(memberRole));
    }
}