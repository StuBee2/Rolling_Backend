package rolling.application.auth.outport;

import rolling.domain.member.consts.MemberRole;

public interface ProvideTokenPort {

    String generateAccessToken(Long id, MemberRole memberRole);

    String generateRefreshToken(Long id, MemberRole memberRole);

}