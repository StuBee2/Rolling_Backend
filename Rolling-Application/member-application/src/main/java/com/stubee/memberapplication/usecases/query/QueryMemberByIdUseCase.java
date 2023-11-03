package com.stubee.memberapplication.usecases.query;

public interface QueryMemberByIdUseCase {

    MemberResponse get(Long memberId);

}