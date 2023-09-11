package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SocialDetails(
        String socialId,
        String socialLoginId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {
    public static SocialDetails create(final String socialId, final String socialLoginId, final LoginType loginType,
                                       final String name, final String email, final String imageUrl) {
        return SocialDetails.builder()
                .socialId(socialId)
                .socialLoginId(socialLoginId)
                .loginType(loginType)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }

    public SocialDetails updateLoginId(final String socialLoginId) {
        return SocialDetails.builder()
                .socialId(socialId)
                .socialLoginId(socialLoginId)
                .loginType(loginType)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }

    public SocialDetails updateEmail(final String email) {
        return SocialDetails.builder()
                .socialId(socialId)
                .loginType(loginType)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }
}