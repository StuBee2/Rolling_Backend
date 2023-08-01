package com.stubee.rollinginfrastructure.global.jwt.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingcore.domain.auth.enums.JwtType;
import com.stubee.rollingcore.domain.auth.exception.WrongTokenTypeException;
import com.stubee.rollingcore.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollinginfrastructure.global.jwt.properties.JwtProperties;
import com.stubee.rollinginfrastructure.global.security.model.CustomMemberDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class ParseJwtAdapter implements ParseJwtPort {

    private final QueryMemberPort queryMemberPort;
    private final JwtProperties jwtProperties;

    @Override
    public Jws<Claims> getClaims(final String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getAccessKey()).parseClaimsJws(token);
    }

    @Override
    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        this.isWrongType(claims, JwtType.ACCESS);

        final Member member = queryMemberPort.findById(UUID.fromString(claims.getBody().getSubject()))
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        log.info("memberSocialId : {}", member.socialDetails().socialId());

        final CustomMemberDetails details = new CustomMemberDetails(member);

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

    @Override
    public void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        if(!(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()))) {
            throw WrongTokenTypeException.EXCEPTION;
        }
    }

}