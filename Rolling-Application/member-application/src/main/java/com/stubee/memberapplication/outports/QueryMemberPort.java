package com.stubee.memberapplication.outports;

import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;

import java.util.Optional;
import java.util.UUID;

public interface QueryMemberPort extends CheckNicknameDuplicationPort {

    Optional<Member> findById(UUID id);

    default Member getById(final UUID id) {
        return findById(id)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

}