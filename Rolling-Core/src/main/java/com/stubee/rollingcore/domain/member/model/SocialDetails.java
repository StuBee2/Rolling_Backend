package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import lombok.Builder;

@Builder
public record SocialDetails(
        String socialId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {}