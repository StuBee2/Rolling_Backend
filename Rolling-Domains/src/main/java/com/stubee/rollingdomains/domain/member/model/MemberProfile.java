package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;

public record MemberProfile (
        String socialId,
        String socialLoginId,
        String name,
        String email,
        String imageUrl,
        MemberRole memberRole,
        LoginType loginType) {
    public Member toMember() {
        return Member.ExceptIdBuilder()
                .socialDetails(SocialDetails.AllArgsBuilder()
                        .socialId(socialId)
                        .socialLoginId(socialLoginId)
                        .loginType(loginType)
                        .name(name)
                        .email(email)
                        .imageUrl(imageUrl)
                        .build())
                .memberDetails(MemberDetails.OnlyWithRoleBuilder()
                        .memberRole(memberRole)
                        .build())
                .build();
    }
}