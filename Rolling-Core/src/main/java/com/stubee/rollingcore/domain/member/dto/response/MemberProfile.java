package com.stubee.rollingcore.domain.member.dto.response;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import com.stubee.rollingcore.domain.member.model.Member;

public record MemberProfile (
        /*@NotNull*/ String socialId,
        String name,
        String email,
        /*@NotBlank*/ String imageUrl,
        /*@NotBlank*/ MemberRole memberRole,
        /*@NotBlank*/ LoginType loginType) {
    public Member toMember() {
        return Member.builder()
                .socialId(socialId)
                .name(name)
                .email(email)
                .loginType(loginType)
                .memberRole(memberRole)
                .imageUrl(imageUrl)
                .build();
    }

}