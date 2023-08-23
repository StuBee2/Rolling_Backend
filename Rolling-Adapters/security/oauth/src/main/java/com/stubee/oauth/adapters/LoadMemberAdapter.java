package com.stubee.oauth.adapters;

import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.securitycommons.annotation.Adapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Adapter
public class LoadMemberAdapter implements MemberSecurityPort {

    @Override
    public Member getCurrentMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

    @Override
    public MemberId getCurrentMemberId() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMemberId();
    }

}