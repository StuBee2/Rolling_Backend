package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.events.MemberCertifiedEvent;

public interface ElevateMemberRoleService {

    void elevate(MemberCertifiedEvent event);

}