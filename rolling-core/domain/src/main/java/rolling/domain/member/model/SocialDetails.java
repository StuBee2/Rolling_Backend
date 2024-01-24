package rolling.domain.member.model;

import rolling.domain.member.consts.LoginType;

public record SocialDetails(String socialId, String socialLoginId, LoginType loginType) {}