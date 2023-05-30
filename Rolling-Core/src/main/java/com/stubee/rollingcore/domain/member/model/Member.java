package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Member (
        UUID id,
        String nickName,
        String socialId,
        String name,
        String email,
        String imageUrl,
        MemberRole memberRole,
        LoginType loginType,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}