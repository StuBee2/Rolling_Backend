package rolling.security.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.auth.outport.ProvideTokenPort;
import rolling.domain.member.consts.MemberRole;

import java.util.Date;

@Component
@RequiredArgsConstructor
class ProvideJwtAdapter implements ProvideTokenPort {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(final Long id, final MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(id.toString())
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtProperties.getAccessExpire())))
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
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtProperties.getRefreshExpire())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

}