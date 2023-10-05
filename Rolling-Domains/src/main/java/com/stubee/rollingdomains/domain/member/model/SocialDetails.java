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
        Objects.requireNonNull(socialId, "SocialId can not be null");
        Objects.requireNonNull(socialLoginId, "SocialLoginId can not be null");
        Objects.requireNonNull(loginType, "LoginType can not be null");
        Objects.requireNonNull(name, "Name can not be null");
    }

    public SocialDetails updateLoginId(final String socialLoginId) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }

    public SocialDetails updateEmail(final String email) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }
}