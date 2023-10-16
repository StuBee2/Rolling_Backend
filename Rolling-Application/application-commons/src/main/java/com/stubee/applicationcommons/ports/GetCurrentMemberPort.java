package com.stubee.applicationcommons.ports;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface GetCurrentMemberPort {

    Member getMember();

    MemberId getMemberId();

}