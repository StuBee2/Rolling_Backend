package rolling.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rolling.application.auth.outport.ParseTokenPort;
import rolling.application.member.outport.QueryMemberPort;
import rolling.domain.member.model.Member;
import rolling.security.oauth.MemberDetailsAdapter;

@Component
@Slf4j
@RequiredArgsConstructor
class JwtHelper {

    private final ParseTokenPort parseTokenPort;
    private final QueryMemberPort queryMemberPort;

    void setAuthentication(final String method, final String accessToken) {
        if(accessToken != null) {
            final Long memberId = parseTokenPort.getSubjectFromAccessToken(accessToken);

            final Member member = queryMemberPort.getBy(memberId);

            logLoginId(method, member.socialDetails().socialLoginId());

            final Authentication authentication = getAuthentication(member);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Authentication getAuthentication(final Member member) {
        final MemberDetailsAdapter details = MemberDetailsAdapter.of(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private void logLoginId(final String method, final String socialLoginId) {
        switch (method) {
            case "POST", "PATCH", "DELETE" -> log.info("{} | LoginId : {}", method, socialLoginId);
        }
    }

}