package com.stubee.oauth.adapters;

import com.stubee.applicationcommons.ports.LoadCurrentMemberPort;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.securitycommons.annotation.Adapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Adapter
public class LoadCurrentMemberAdapter implements LoadCurrentMemberPort {

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