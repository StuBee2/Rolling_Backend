package com.stubee.oauth.adapters;

import com.stubee.authapplication.outports.ProvideJwtPort;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.securitycommons.annotations.Adapter;
import com.stubee.oauth.properties.JwtProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Adapter
@RequiredArgsConstructor
public class ProvideJwtAdapter implements ProvideJwtPort {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(final Long id, final MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(id.toString())
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getAccessKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(final Long id, final MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(id.toString())
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

}