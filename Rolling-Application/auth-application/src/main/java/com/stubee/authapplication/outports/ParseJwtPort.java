package com.stubee.authapplication.outports;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.Authentication;

public interface ParseJwtPort {

    Jws<Claims> getClaimsFromRefreshToken(String refreshToken);

    Authentication getAuthenticationFromToken(String token);

}