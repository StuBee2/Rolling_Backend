package com.stubee.rollinginfrastructure.common.jwt.adapter;

import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingcore.domain.auth.exception.AccessTokenRequireException;
import com.stubee.rollingcore.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollinginfrastructure.common.jwt.properties.JwtProperties;
import com.stubee.rollinginfrastructure.common.security.oauth.principle.CustomMemberDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ParseJwtAdapter implements ParseJwtPort {

    private final QueryMemberPort queryMemberPort;
    private final JwtProperties jwtProperties;

    @Override
    public Jws<Claims> getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getAccessKey()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
    }

    @Override
    public Authentication getAuthentication(final String token) {
        Jws<Claims> claims = getClaims(token);

        if(!claims.getHeader().get(Header.JWT_TYPE).equals("ACCESS")) {
            throw AccessTokenRequireException.EXCEPTION;
        }

        Member member = queryMemberPort.findById(UUID.fromString(claims.getBody().getSubject()))
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        CustomMemberDetails details = new CustomMemberDetails(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    @Override
    public String extractTokenFromRequest(final HttpServletRequest request) {
        return extractToken(request.getHeader("Authorization"));
    }

    @Override
    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}