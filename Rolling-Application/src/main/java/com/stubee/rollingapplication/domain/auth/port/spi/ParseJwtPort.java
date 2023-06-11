package com.stubee.rollingapplication.domain.auth.port.spi;

import com.stubee.rollingcore.domain.auth.enums.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface ParseJwtPort {

    Jws<Claims> getClaims(String token);

    Authentication getAuthentication(String token);

    String extractTokenFromRequest(HttpServletRequest request);

    String extractToken(String token);

    boolean isWrongType(Jws<Claims> claims, JwtType jwtType);

}