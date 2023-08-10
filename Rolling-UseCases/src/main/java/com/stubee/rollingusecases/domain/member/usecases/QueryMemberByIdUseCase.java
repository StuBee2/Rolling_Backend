package com.stubee.rollingusecases.domain.member.usecases;

import com.stubee.rollingdomains.domain.member.model.Member;

import java.util.UUID;

public interface QueryMemberByIdUseCase {

    Member get(UUID memberId);

}