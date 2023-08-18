package com.stubee.applicationcommons.ports.member;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface MemberSecurityPort {

    Member getCurrentMember();

    MemberId getCurrentMemberId();

}