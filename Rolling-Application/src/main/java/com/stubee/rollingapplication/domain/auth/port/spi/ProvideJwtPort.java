package com.stubee.rollingapplication.domain.auth.port.spi;

import com.stubee.rollingcore.domain.member.enums.MemberRole;

import java.util.UUID;

public interface ProvideJwtPort {

    String generateAccessToken(UUID id, MemberRole memberRole);

    String generateRefreshToken(UUID id, MemberRole memberRole);

}