package com.stubee.authapplication.outports;

import com.stubee.rollingdomains.domain.member.consts.MemberRole;

import java.util.UUID;

public interface ProvideJwtPort {

    String generateAccessToken(UUID id, MemberRole memberRole);

    String generateRefreshToken(UUID id, MemberRole memberRole);

}