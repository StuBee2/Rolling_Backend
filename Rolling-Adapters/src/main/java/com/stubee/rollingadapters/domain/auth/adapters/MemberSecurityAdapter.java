package com.stubee.rollingadapters.domain.auth.adapters;

import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingexternal.global.security.model.CustomMemberDetails;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import org.springframework.security.core.context.SecurityContextHolder;

@Adapter
public class MemberSecurityAdapter implements MemberSecurityPort {

    @Override
    public Member getCurrentMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

    @Override
    public MemberId getCurrentMemberId() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMemberId();
    }

}