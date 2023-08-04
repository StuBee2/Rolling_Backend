package com.stubee.rollingcore.domain.member.enums;

import com.stubee.rollingcore.domain.auth.exception.WrongLoginTypeException;
import com.stubee.rollingcore.domain.member.model.MemberProfile;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public enum OAuthAttributes {

    GITHUB(LoginType.GITHUB, (attributes) ->
            new MemberProfile(
                    (Integer) attributes.get("id"),
                    (String) attributes.get("login"),
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("avatar_url"),
                    MemberRole.MEMBER,
                    LoginType.GITHUB)
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