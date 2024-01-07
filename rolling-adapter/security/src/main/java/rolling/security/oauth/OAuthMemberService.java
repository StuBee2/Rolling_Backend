package rolling.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.CommandMemberPort;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.model.Member;

import java.util.Locale;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
class OAuthMemberService extends DefaultOAuth2UserService {

    private final CommandMemberPort commandMemberPort;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        final Map<String, Object> attributes = super.loadUser(oAuth2UserRequest).getAttributes();

        final LoginType loginType = getLoginType(oAuth2UserRequest);

        final Member member = commandMemberPort.saveOrUpdate(OAuthAttributes.toProfile(loginType, attributes));

        return MemberDetailsAdapter.of(member, attributes);
    }

    private LoginType getLoginType(final OAuth2UserRequest oAuth2UserRequest) {
        return LoginType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
    }

}