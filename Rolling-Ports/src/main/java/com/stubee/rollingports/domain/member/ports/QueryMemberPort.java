package com.stubee.rollingports.domain.member.ports;

import com.stubee.rollingdomains.domain.member.model.Member;

import java.util.Optional;
import java.util.UUID;

public interface QueryMemberPort {

    Optional<Member> findById(UUID id);

}