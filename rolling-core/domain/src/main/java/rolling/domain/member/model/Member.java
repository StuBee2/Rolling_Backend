package rolling.domain.member.model;

import lombok.Builder;
import rolling.domain.common.error.Assert;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.exception.DuplicatedNicknameException;
import rolling.domain.member.exception.WrongLoginTypeException;
import rolling.domain.member.service.MemberService;

import java.util.Objects;

public final class Member {

    private final MemberId memberId;
    private SocialDetails socialDetails;
    private MemberDetails memberDetails;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Member(SocialDetails socialDetails, MemberDetails memberDetails) {
        this(null, socialDetails, memberDetails);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Member(MemberId memberId, SocialDetails socialDetails, MemberDetails memberDetails) {
        Assert.notNull(socialDetails, "SocialDetails must not be null");
        Assert.notNull(memberDetails, "MemberDetails must not be null");
        this.memberId = memberId;
        this.socialDetails = socialDetails;
        this.memberDetails = memberDetails;
    }

    public void modifyLoginId(final String socialLoginId, final LoginType loginType) {
        isEqualLoginType(loginType);

        this.socialDetails = new SocialDetails(socialDetails.socialId(), socialLoginId, socialDetails.loginType(),
                socialDetails.name(), socialDetails.email(), socialDetails.imageUrl());
    }

    public void modifyNickname(final String nickname, final MemberService memberService) {
        if(!Objects.equals(memberDetails.nickName(), nickname)) {
            if(memberService.isNicknameDuplicate(nickname)) {
                throw DuplicatedNicknameException.EXCEPTION;
            }
        }

        this.memberDetails = new MemberDetails(nickname, memberDetails.memberRole(),
                memberDetails.createdAt(), memberDetails.modifiedAt());
    }

    public void elevateToMember() {
        this.memberDetails = new MemberDetails(memberDetails.nickName(), MemberRole.MEMBER,
                memberDetails.createdAt(), memberDetails.modifiedAt());
    }

    private void isEqualLoginType(final LoginType loginType) {
        if (socialDetails.loginType() != loginType) {
            throw WrongLoginTypeException.EXCEPTION;
        }
    }

    public MemberId memberId() {
        return memberId;
    }

    public SocialDetails socialDetails() {
        return socialDetails;
    }

    public MemberDetails memberDetails() {
        return memberDetails;
    }

}