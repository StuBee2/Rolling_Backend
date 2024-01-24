package rolling.domain.member.model;

import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;

public record MemberProfile (
        String socialId,
        String socialLoginId,
        String name,
        String email,
        String imageUrl,
        MemberRole memberRole,
        LoginType loginType) {
    public Member toMember() {
        return Member.ExceptIdBuilder()
                .details(new MemberDetails(null, name, email, imageUrl))
                .socialDetails(new SocialDetails(socialId, socialLoginId, loginType))
                .build();
    }
}