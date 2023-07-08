package com.stubee.rollingapplication.domain.member.port.spi;

import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;

public interface MemberSecurityPort {

    Member getCurrentMember();

    MemberId getCurrentMemberId();

}