package com.stubee.rollingadapter.in.web.member;

import com.stubee.rollingcore.domain.member.dto.response.MemberInfoResponse;
import com.stubee.rollingapplication.domain.member.port.api.QueryMemberUseCase;
import com.stubee.rollingcore.domain.member.model.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Query Member", description = "Query Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class QueryMemberController {

    private final QueryMemberUseCase queryMemberUseCase;

    @Operation(description = "내 정보 조회")
    @GetMapping
    @ResponseStatus(OK)
    public Member getMy() {
        return queryMemberUseCase.getMy();
    }

    @Operation(description = "MemberId로 Member 정보 조회")
    @GetMapping("/{id}")
    public Member getMember(@PathVariable("id") UUID memberId) {
        return queryMemberUseCase.getMemberById(memberId);
    }

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public MemberInfoResponse getMyInfo() {
        return queryMemberUseCase.getMyInfo();
    }

    /**
     * @deprecated 내 정보 api 분리로 인해 삭제 에정
     * */
    @Deprecated
    @Operation(description = "id로 Member 정보 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public MemberInfoResponse getInfo(final @PathVariable("id") UUID memberId) {
        return queryMemberUseCase.getInfo(memberId);
    }

}