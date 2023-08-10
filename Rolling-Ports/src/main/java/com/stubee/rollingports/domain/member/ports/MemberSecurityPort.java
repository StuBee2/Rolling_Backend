package com.stubee.rollingports.domain.member.ports;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface MemberSecurityPort {

    Member getCurrentMember();

    MemberId getCurrentMemberId();

}