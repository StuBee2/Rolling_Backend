package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.exception.WrongLoginTypeException;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Member (
        MemberId memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    public static Member create(final MemberId memberId, final SocialDetails socialDetails, final MemberDetails memberDetails) {
        return Member.builder()
                .memberId(memberId)
                .socialDetails(socialDetails)
                .memberDetails(memberDetails)
                .build();
    }

    public static Member createExceptId(final SocialDetails socialDetails, final MemberDetails memberDetails) {
        return Member.builder()
                .socialDetails(socialDetails)
                .memberDetails(memberDetails)
                .build();
    }

    public Member updateLoginId(final String socialLoginId) {
        return create(memberId, socialDetails.updateLoginId(socialLoginId), memberDetails);
    }

    public Member changeEmail(final String email) {
        return create(memberId, socialDetails.updateEmail(email), memberDetails);
    }

    public Member changeNickname(final String nickname) {
        return create(memberId, socialDetails, memberDetails.updateNickName(nickname));
    }

    public Member elevateToMember() {
        return create(memberId, socialDetails, memberDetails.elevateToMember());
    }

    public void isEqualLoginType(final LoginType loginType) {
        if(socialDetails.loginType()==loginType) {
            throw WrongLoginTypeException.EXCEPTION;
        }
    }
}