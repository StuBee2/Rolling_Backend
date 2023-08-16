package com.stubee.rollingapi.global.security.adapters;

import com.stubee.rollingapi.global.security.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.thirdparty.common.annotations.Adapter;
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