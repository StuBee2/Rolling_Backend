package com.stubee.memberapplication.usecases.query;

import com.stubee.rollingdomains.common.model.TSID;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;

public record MemberResponse(
        TSID memberId,
        SocialDetails socialDetails,
        MemberDetails memberDetails) {
    public static MemberResponse of(Member member) {
        return new MemberResponse(TSID.of(member.memberId()), member.socialDetails(), member.memberDetails());
    }
}