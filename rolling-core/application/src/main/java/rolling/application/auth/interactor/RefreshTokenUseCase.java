package rolling.application.auth.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.auth.outport.ParseTokenPort;
import rolling.application.auth.outport.ProvideTokenPort;
import rolling.application.member.outport.QueryMemberPort;
import rolling.domain.member.model.Member;

@Component
@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final ProvideTokenPort provideJwtPort;
    private final ParseTokenPort parseJwtPort;
    private final QueryMemberPort queryMemberPort;

    public RefreshTokenResponse refresh(final String refreshToken) {
        final Long memberId = parseJwtPort.getSubjectFromRefreshToken(refreshToken);

        final Member member = queryMemberPort.getBy(memberId);

        final String accessToken = provideJwtPort.generateAccessToken(member.id().getId(), member.role());

        return RefreshTokenResponse.of(accessToken);
    }

}