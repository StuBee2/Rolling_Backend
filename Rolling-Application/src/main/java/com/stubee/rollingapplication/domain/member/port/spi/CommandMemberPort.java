package com.stubee.rollingapplication.domain.member.port.spi;

import com.stubee.rollingcore.domain.member.response.MemberProfile;
import com.stubee.rollingcore.domain.member.model.Member;

public interface CommandMemberPort {

    Member saveWithId(Member member);

    Member saveExceptId(Member member);

    Member saveOrUpdate(MemberProfile memberProfile);

}