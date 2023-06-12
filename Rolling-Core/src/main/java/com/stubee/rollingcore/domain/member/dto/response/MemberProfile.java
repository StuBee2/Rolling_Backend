package com.stubee.rollingcore.domain.member.dto.response;

import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberDetails;
import com.stubee.rollingcore.domain.member.model.SocialDetails;

public record MemberProfile (
        /*@NotNull*/ String socialId,
        String name,
        String email,
        /*@NotBlank*/ String imageUrl,
        /*@NotBlank*/ MemberRole memberRole,
        /*@NotBlank*/ LoginType loginType) {
    public Member toMember() {
        return Member.createExceptId(SocialDetails.create(socialId, loginType, name, email, imageUrl),
                MemberDetails.createOnlyMemberRole(memberRole));
    }

}