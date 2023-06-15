package com.stubee.rollingapplication.domain.member.dto.response;

import com.stubee.rollingcore.domain.auth.exception.WrongLoginTypeException;
import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
public enum OAuthAttributes {

    GITHUB(LoginType.GITHUB, (attributes) ->
            new MemberProfile(
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