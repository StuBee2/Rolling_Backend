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
                .socialDetails(SocialDetails.builder()
                        .socialId(socialId)
                        .socialLoginId(socialLoginId)
                        .loginType(loginType)
                        .name(name)
                        .email(email)
                        .imageUrl(imageUrl)
                        .build())
                .memberDetails(new MemberDetails(null, memberRole, null, null))
                .build();
    }
}