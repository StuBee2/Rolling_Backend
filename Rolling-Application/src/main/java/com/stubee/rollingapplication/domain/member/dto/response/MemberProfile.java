package com.stubee.rollingapplication.domain.member.dto.response;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberDetails;
import com.stubee.rollingcore.domain.member.model.SocialDetails;

public record MemberProfile (
        String socialId,
        String name,
        String email,
        String imageUrl,
        MemberRole memberRole,
        LoginType loginType) {
    public Member toMember() {
        return Member.createExceptId(SocialDetails.create(socialId, loginType, name, email, imageUrl),
                MemberDetails.createOnlyMemberRole(memberRole));
    }

}