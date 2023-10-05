package com.stubee.rollingdomains.domain.member.model;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.exception.WrongLoginTypeException;
import lombok.Builder;

import java.util.Objects;

public record Member (
        MemberId memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Member(SocialDetails socialDetails, MemberDetails memberDetails) {
        this(null, socialDetails, memberDetails);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Member {
        Objects.requireNonNull(socialDetails, "SocialDetails can not be null");
        Objects.requireNonNull(memberDetails, "MemberDetails can not be null");
    }

    public Member updateLoginId(final String socialLoginId) {
        return new Member(memberId, socialDetails.updateLoginId(socialLoginId), memberDetails);
    }

    public Member changeEmail(final String email) {
        return new Member(memberId, socialDetails.updateEmail(email), memberDetails);
    }

    public Member changeNickname(final String nickname) {
        return new Member(memberId, socialDetails, memberDetails.updateNickName(nickname));
    }

    public Member elevateToMember() {
        return new Member(memberId, socialDetails, memberDetails.elevateToMember());
    }

    public void isEqualLoginType(final LoginType loginType) {
        if(socialDetails.loginType()!=loginType) {
            throw WrongLoginTypeException.EXCEPTION;
        }
    }
}