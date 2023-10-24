package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import lombok.Builder;

public record SocialDetails(
        String socialId,
        String socialLoginId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {
    @Builder(builderClassName = "AllArgsBuilder", builderMethodName = "AllArgsBuilder")
    public SocialDetails {
        Assert.notNull(socialId, "SocialId must not be null");
        Assert.notNull(socialLoginId, "SocialLoginId must not be null");
        Assert.notNull(loginType, "LoginType must not be null");
        Assert.notNull(name, "Name must not be null");
    }

    SocialDetails updateLoginId(final String socialLoginId) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }

    SocialDetails updateEmail(final String email) {
        return new SocialDetails(socialId, socialLoginId, loginType, name, email, imageUrl);
    }
}