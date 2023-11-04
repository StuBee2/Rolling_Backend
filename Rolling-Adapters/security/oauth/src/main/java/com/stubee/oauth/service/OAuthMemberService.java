package com.stubee.oauth.service;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.oauth.model.consts.OAuthAttributes;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Locale;
import java.util.Map;

@CommandService
@RequiredArgsConstructor
public class OAuthMemberService extends DefaultOAuth2UserService {

    private final CommandMemberPort commandMemberPort;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        final Map<String, Object> attributes = super.loadUser(oAuth2UserRequest).getAttributes();

        final LoginType loginType = getLoginType(oAuth2UserRequest);

        final Member member = commandMemberPort.saveOrUpdate(
                OAuthAttributes.toProfile(loginType, attributes));

        return CustomMemberDetails.create(member, attributes);
    }

    private LoginType getLoginType(final OAuth2UserRequest oAuth2UserRequest) {
        return LoginType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
    }

}