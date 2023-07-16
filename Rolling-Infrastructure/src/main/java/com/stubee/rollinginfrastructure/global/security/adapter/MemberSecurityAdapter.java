package com.stubee.rollinginfrastructure.global.security.adapter;

import com.stubee.rollingapplication.common.annotation.Adapter;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollinginfrastructure.global.security.oauth.principle.CustomMemberDetails;
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