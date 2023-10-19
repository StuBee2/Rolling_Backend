package com.stubee.memberapplication.usecases;

import com.stubee.rollingdomains.domain.member.model.Member;

public interface QueryMemberByIdUseCase {

    Member get(Long memberId);

}