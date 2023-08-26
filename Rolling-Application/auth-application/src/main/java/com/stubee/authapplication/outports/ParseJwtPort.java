package com.stubee.authapplication.outports;

import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface ParseJwtPort {

    Jws<Claims> getClaims(String token);

    Authentication getAuthentication(String token);

    String extractTokenFromRequest(HttpServletRequest request);

    void isWrongType(Jws<Claims> claims, JwtType jwtType);

}