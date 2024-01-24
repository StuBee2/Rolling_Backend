package rolling.domain.member.model;

import lombok.Builder;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.exception.DuplicatedNicknameException;
import rolling.domain.member.exception.WrongLoginTypeException;
import rolling.domain.member.service.MemberService;

import java.time.LocalDateTime;

public final class Member {

    private final MemberId id;
    private MemberRole role;
    private MemberDetails details;
    private SocialDetails socialDetails;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Member(final SocialDetails socialDetails, final MemberDetails details) {
        this(null, MemberRole.TEMP, socialDetails, details, null, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Member(final MemberId id, final MemberRole role, final SocialDetails socialDetails, final MemberDetails details,
                  final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this.id = id;
        this.role = role;
        this.details = details;
        this.socialDetails = socialDetails;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void modify(final String socialLoginId, final LoginType loginType) {
        isEqualLoginType(loginType);

        this.socialDetails = new SocialDetails(socialDetails.socialId(), socialLoginId, socialDetails.loginType());
    }

    public void modify(final String nickname, final MemberService memberService) {
        if(!details.nickName().equals(nickname)) {
            if(memberService.isNicknameDuplicate(nickname)) {
                throw DuplicatedNicknameException.EXCEPTION;
            }
        }

        this.details = new MemberDetails(nickname, details.name(), details.email(), details.imageUrl());
    }

    public void modifyRoleToMember() {
        this.role = MemberRole.MEMBER;
    }

    private void isEqualLoginType(final LoginType loginType) {
        if (socialDetails.loginType() != loginType) {
            throw WrongLoginTypeException.EXCEPTION;
        }
    }

    public MemberId id() {
        return id;
    }

    public MemberRole role() {
        return role;
    }

    public MemberDetails details() {
        return details;
    }

    public SocialDetails socialDetails() {
        return socialDetails;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

}