package com.stubee.memberapplication.outports;

import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;

import java.util.Optional;

public interface QueryMemberPort extends CheckNicknameDuplicationPort {

    Optional<Member> findById(Long id);

    default Member getById(final Long id) {
        return findById(id)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

}