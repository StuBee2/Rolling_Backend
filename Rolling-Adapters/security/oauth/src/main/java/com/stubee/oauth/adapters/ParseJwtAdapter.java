package com.stubee.oauth.adapters;

import com.stubee.authapplication.outports.ParseTokenPort;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.oauth.exception.WrongTokenTypeException;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.securitycommons.annotations.Adapter;
import com.stubee.oauth.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

@Adapter
@Slf4j
@RequiredArgsConstructor
public class ParseJwtAdapter implements ParseTokenPort {

    private final QueryMemberPort queryMemberPort;
    private final JwtProperties jwtProperties;

    @Override
    public Long getSubjectFromRefreshToken(final String refreshToken) {
        return getSubject(refreshToken, JwtType.REFRESH);
    }

    @Override
    public Authentication getAuthenticationFromToken(final String token) {
        final Long memberId = getSubject(token, JwtType.ACCESS);

        final Member member = queryMemberPort.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        log.info("SocialLoginId : {}", member.socialDetails().socialLoginId());

        final CustomMemberDetails details = CustomMemberDetails.create(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private Long getSubject(final String token, final JwtType jwtType) {
        final String key = jwtType==JwtType.ACCESS ? jwtProperties.getAccessKey() : jwtProperties.getSecretKey();

        final Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(extractToken(token));

        this.isWrongType(jwsClaims, jwtType);

        return Long.parseLong(jwsClaims.getBody().getSubject());
    }

    private void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
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