package com.stubee.rollingports.domain.auth.ports;

import com.stubee.rollingdomains.domain.member.consts.MemberRole;

import java.util.UUID;

public interface ProvideJwtPort {

    String generateAccessToken(UUID id, MemberRole memberRole);

    String generateRefreshToken(UUID id, MemberRole memberRole);

}