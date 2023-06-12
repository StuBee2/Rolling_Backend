package com.stubee.rollingcore.domain.member.model;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Member (
        MemberId memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    public static Member create(MemberId memberId, SocialDetails socialDetails, MemberDetails memberDetails) {
        return Member.builder()
                .memberId(memberId)
                .socialDetails(socialDetails)
                .memberDetails(memberDetails)
                .build();
    }

    public static Member createExceptId(SocialDetails socialDetails, MemberDetails memberDetails) {
        return Member.builder()
                .socialDetails(socialDetails)
                .memberDetails(memberDetails)
                .build();
    }

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
        return socialDetails.updateNameAndEmail(name, email);
    }

    private MemberDetails updateNickName(final String nickName) {
        return memberDetails.updateNickName(nickName);
    }
}