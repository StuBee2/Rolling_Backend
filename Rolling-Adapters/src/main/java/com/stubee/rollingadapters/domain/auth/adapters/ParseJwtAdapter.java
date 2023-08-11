package com.stubee.rollingadapters.domain.auth.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.auth.exception.WrongTokenTypeException;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingexternal.global.jwt.properties.JwtProperties;
import com.stubee.rollingexternal.global.security.model.CustomMemberDetails;
import com.stubee.rollingports.domain.auth.ports.ParseJwtPort;
import com.stubee.rollingports.domain.member.ports.QueryMemberPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Adapter
@Slf4j
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

        log.info("memberSocialId : {}", member.socialDetails().socialLoginId());
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