package com.stubee.oauth.helper;

import com.stubee.authapplication.outports.ParseTokenPort;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
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

    public void setAuthentication(final String accessToken) {
        if(accessToken != null) {
            final Long memberId = parseTokenPort.getSubjectFromAccessToken(accessToken);

            final Member member = queryMemberPort.findById(memberId)
                    .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

            log.info("LoginId : {}", member.socialDetails().socialLoginId());

            final CustomMemberDetails details = CustomMemberDetails.create(member);

            Authentication authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}