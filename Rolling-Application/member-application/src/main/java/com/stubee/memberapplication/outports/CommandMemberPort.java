package com.stubee.memberapplication.outports;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberProfile;

public interface CommandMemberPort {

    Member saveWithId(Member member);

    Member saveExceptId(Member member);

    Member saveOrUpdate(MemberProfile memberProfile);

}