package com.stubee.memberapplication.usecases;

import com.stubee.memberapplication.usecases.response.MemberResponse;

public interface QueryMemberByIdUseCase {

    MemberResponse get(Long memberId);

}