package com.stubee.authapplication.outports;

import org.springframework.security.core.Authentication;

public interface ParseTokenPort {

    Long getSubjectFromRefreshToken(String refreshToken);

    Authentication getAuthenticationFromToken(String token);

}