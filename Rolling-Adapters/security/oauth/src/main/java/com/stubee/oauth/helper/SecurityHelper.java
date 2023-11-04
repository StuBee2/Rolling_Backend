package com.stubee.oauth.helper;

import com.stubee.authapplication.outports.ParseTokenPort;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityHelper {

    private final ParseTokenPort parseTokenPort;
    private final QueryMemberPort queryMemberPort;

    public void setAuthentication(final String method, final String accessToken) {
        if(accessToken != null) {
            final Long memberId = parseTokenPort.getSubjectFromAccessToken(accessToken);

            final Member member = queryMemberPort.getById(memberId);

            this.logLoginId(method, member.socialDetails().socialLoginId());

            final Authentication authentication = getAuthentication(member);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Authentication getAuthentication(final Member member) {
        final CustomMemberDetails details = CustomMemberDetails.create(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private void logLoginId(final String method, final String socialLoginId) {
        switch (method) {
            case "POST", "PATCH", "DELETE" -> log.info("{} | LoginId : {}", method, socialLoginId);
        }
    }

}