package com.stubee.oauth.adapters;

import com.stubee.authapplication.outports.ParseJwtPort;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.auth.exception.WrongTokenTypeException;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.securitycommons.annotations.Adapter;
import com.stubee.oauth.properties.JwtProperties;
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
        return Jwts.parser().setSigningKey(jwtProperties.getAccessKey()).parseClaimsJws(extractToken(token));
    }

    @Override
    public Jws<Claims> getClaimsWithRefreshToken(final String refreshToken) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(extractToken(refreshToken));
    }

    @Override
    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        this.isWrongType(claims, JwtType.ACCESS);

        final Member member = queryMemberPort.findById(UUID.fromString(claims.getBody().getSubject()))
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        log.info("Member Role : {}", member.memberDetails().memberRole());
        log.info("SocialLoginId : {}", member.socialDetails().socialLoginId());

        final CustomMemberDetails details = CustomMemberDetails.create(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    @Override
    public String extractTokenFromRequest(final HttpServletRequest request) {
        return extractToken(request.getHeader("Authorization"));
    }

    @Override
    public void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        if(!(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()))) {
            throw WrongTokenTypeException.EXCEPTION;
        }
    }

    private String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}