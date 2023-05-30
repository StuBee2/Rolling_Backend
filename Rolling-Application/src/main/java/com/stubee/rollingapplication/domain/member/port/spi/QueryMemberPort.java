package com.stubee.rollingapplication.domain.member.port.spi;

import com.stubee.rollingcore.domain.member.model.Member;

import java.util.Optional;
import java.util.UUID;

public interface QueryMemberPort {

    Optional<Member> findById(UUID id);

}