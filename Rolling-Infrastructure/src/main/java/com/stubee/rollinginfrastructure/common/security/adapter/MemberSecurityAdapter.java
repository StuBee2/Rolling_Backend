package com.stubee.rollinginfrastructure.common.security.adapter;

import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollinginfrastructure.common.security.oauth.principle.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberSecurityAdapter implements MemberSecurityPort {

    @Override
    public Member getCurrentMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

}