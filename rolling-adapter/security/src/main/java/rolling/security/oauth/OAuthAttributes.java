package rolling.security.oauth;

import lombok.RequiredArgsConstructor;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.exception.WrongLoginTypeException;
import rolling.domain.member.model.MemberProfile;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
enum OAuthAttributes {

    GITHUB(LoginType.GITHUB, (attributes) ->
            new MemberProfile(
                    String.valueOf(attributes.get("id")),
                    (String) attributes.get("login"),
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("avatar_url"),
                    MemberRole.TEMP,
                    LoginType.GITHUB)
    ),

    GOOGLE(LoginType.GOOGLE, (attributes) ->
            new MemberProfile(
                    (String) attributes.get("sub"),
                    (String) attributes.get("email"),
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("picture"),
                    MemberRole.TEMP,
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