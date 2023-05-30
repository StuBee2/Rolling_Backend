package com.stubee.rollingapplication.domain.member.port.spi;

import com.stubee.rollingcore.domain.member.model.Member;

public interface MemberSecurityPort {

    Member getCurrentMember();

}