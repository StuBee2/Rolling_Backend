package com.stubee.rollingdomains.domain.member.ports;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface LoadCurrentMemberPort {

    Member getMember();

    MemberId getMemberId();

}