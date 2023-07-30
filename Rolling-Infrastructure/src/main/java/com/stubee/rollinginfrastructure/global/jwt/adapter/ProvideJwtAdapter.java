package com.stubee.rollinginfrastructure.global.jwt.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingapplication.domain.auth.port.spi.ProvideJwtPort;
import com.stubee.rollingcore.domain.auth.enums.JwtType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import com.stubee.rollinginfrastructure.global.jwt.properties.JwtProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class ProvideJwtAdapter implements ProvideJwtPort {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(final UUID id, final MemberRole memberRole) {
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
    public String generateRefreshToken(final UUID id, final MemberRole memberRole) {
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