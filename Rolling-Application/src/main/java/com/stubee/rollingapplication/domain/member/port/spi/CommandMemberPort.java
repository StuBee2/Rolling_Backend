package com.stubee.rollingapplication.domain.member.port.spi;

import com.stubee.rollingcore.domain.member.dto.response.MemberProfile;
import com.stubee.rollingcore.domain.member.model.Member;

public interface CommandMemberPort {

    Member save(Member member);

    Member saveOrUpdate(MemberProfile memberProfile);

}