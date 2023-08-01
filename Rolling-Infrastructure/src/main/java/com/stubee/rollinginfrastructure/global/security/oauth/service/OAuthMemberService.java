package com.stubee.rollinginfrastructure.global.security.oauth.service;

import com.stubee.rollingapplication.domain.member.port.spi.CommandMemberPort;
import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollinginfrastructure.global.security.model.CustomMemberDetails;
import com.stubee.rollingcore.domain.member.enums.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthMemberService extends DefaultOAuth2UserService {

    private final CommandMemberPort commandMemberPort;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = super.loadUser(oAuth2UserRequest).getAttributes();

        LoginType loginType = getLoginType(oAuth2UserRequest);

        Member member = commandMemberPort.saveOrUpdate(
                OAuthAttributes.toProfile(loginType, attributes));

        return CustomMemberDetails.create(member, attributes);
    }

    private LoginType getLoginType(final OAuth2UserRequest oAuth2UserRequest) {
        return LoginType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
    }

}