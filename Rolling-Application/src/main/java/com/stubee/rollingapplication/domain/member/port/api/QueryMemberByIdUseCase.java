package com.stubee.rollingapplication.domain.member.port.api;

import com.stubee.rollingcore.domain.member.model.Member;

import java.util.UUID;

public interface QueryMemberByIdUseCase {

    Member get(UUID memberId);

}