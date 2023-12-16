package com.stubee.oauth.security.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.oauth.security.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.springframework.security.core.context.SecurityContextHolder;

@Adapter
class GetCurrentMemberAdapter implements GetCurrentMemberPort {

    @Override
    public Member getMember() {
        return getMemberDetails().getMember();
    }

    @Override
    public MemberId getMemberId() {
        return getMemberDetails().getMemberId();
    }

    private CustomMemberDetails getMemberDetails() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}