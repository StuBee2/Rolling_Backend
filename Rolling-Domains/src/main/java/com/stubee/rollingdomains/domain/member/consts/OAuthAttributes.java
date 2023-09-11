package com.stubee.rollingdomains.domain.member.consts;

import com.stubee.rollingdomains.domain.auth.exception.WrongLoginTypeException;
import com.stubee.rollingdomains.domain.member.model.MemberProfile;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public enum OAuthAttributes {

    GITHUB(LoginType.GITHUB, (attributes) ->
            new MemberProfile(
                    (String) attributes.get("id"),
                    (String) attributes.get("login"),
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("avatar_url"),
                    MemberRole.MEMBER,
                    LoginType.GITHUB)
    ),

    GOOGLE(LoginType.GOOGLE, (attributes) ->
            new MemberProfile(
                    (String) attributes.get("sub"),
                    (String) attributes.get("email"),
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("picture"),
                    MemberRole.MEMBER,
                    LoginType.GOOGLE)
    );

    private final LoginType registration;
    private final Function<Map<String, Object>, MemberProfile> of;

    public static MemberProfile toProfile(LoginType registration, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registration.equals(provider.registration))
                .findFirst()
                .orElseThrow(() -> WrongLoginTypeException.EXCEPTION)
                .of.apply(attributes);
    }

}