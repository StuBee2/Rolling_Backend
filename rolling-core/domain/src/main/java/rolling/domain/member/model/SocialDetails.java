package rolling.domain.member.model;

import lombok.Builder;
import rolling.domain.member.consts.LoginType;

public record SocialDetails(
        String socialId,
        String socialLoginId,
        LoginType loginType,
        String name,
        String email,
        String imageUrl) {
    @Builder
    public SocialDetails {
    }
}