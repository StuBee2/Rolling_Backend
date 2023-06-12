package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SocialDetails(
        String socialId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {
    public static SocialDetails create(final String socialId, final LoginType loginType, final String name,
                                       final String email, final String imageUrl) {
        return SocialDetails.builder()
                .socialId(socialId)
                .loginType(loginType)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }

    public SocialDetails updateNameAndEmail(final String name, final String email) {
        return SocialDetails.builder()
                .socialId(socialId)
                .loginType(loginType)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }
}