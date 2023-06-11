package com.stubee.rollingcore.domain.member.model;

import lombok.Builder;

@Builder
public record Member (
        MemberId memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    public Member updateSocialDetails(final String name, final String email) {
        return Member.builder()
                .memberId(memberId)
                .socialDetails(updateNameAndEmail(name, email))
                .memberDetails(memberDetails)
                .build();
    }

    public Member updateMemberDetails(final String nickName) {
        return Member.builder()
                .memberId(memberId)
                .socialDetails(socialDetails)
                .memberDetails(updateNickName(nickName))
                .build();
    }

    private SocialDetails updateNameAndEmail(final String name, final String email) {
        return SocialDetails.builder()
                .socialId(socialDetails.socialId())
                .loginType(socialDetails.loginType())
                .name(name)
                .email(email)
                .imageUrl(socialDetails.imageUrl())
                .build();
    }

    private MemberDetails updateNickName(final String nickName) {
        return MemberDetails.builder()
                .nickName(nickName)
                .memberRole(memberDetails.memberRole())
                .createdAt(memberDetails.createdAt())
                .build();
    }
}