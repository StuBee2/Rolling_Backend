package com.stubee.rollingapplication.domain.member.port.api;

import com.stubee.rollingcore.domain.member.dto.response.MemberInfoResponse;
import com.stubee.rollingcore.domain.member.model.Member;

import java.util.UUID;

public interface QueryMemberUseCase {

    Member getMy();

    Member getMemberById(UUID memberId);

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    MemberInfoResponse getMyInfo();

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    MemberInfoResponse getInfo(UUID memberId);

}