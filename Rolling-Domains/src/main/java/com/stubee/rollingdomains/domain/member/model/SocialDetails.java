package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import lombok.Builder;

import java.util.Objects;

public record SocialDetails(
        String socialId,
        String socialLoginId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {
    @Builder(builderClassName = "AllArgsBuilder", builderMethodName = "AllArgsBuilder")
    public SocialDetails {
        Objects.requireNonNull(socialId);
        Objects.requireNonNull(socialLoginId);
        Objects.requireNonNull(loginType);
        Objects.requireNonNull(name);
    }

    public SocialDetails updateLoginId(final String socialLoginId) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }

    public SocialDetails updateEmail(final String email) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }
}