package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface GetMemberInfoService {

    Member getMember();

    MemberId getMemberId();

}