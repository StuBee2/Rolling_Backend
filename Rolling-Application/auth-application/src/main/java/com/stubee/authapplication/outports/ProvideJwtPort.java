package com.stubee.authapplication.outports;

import com.stubee.rollingdomains.domain.member.consts.MemberRole;

public interface ProvideJwtPort {

    String generateAccessToken(Long id, MemberRole memberRole);

    String generateRefreshToken(Long id, MemberRole memberRole);

}