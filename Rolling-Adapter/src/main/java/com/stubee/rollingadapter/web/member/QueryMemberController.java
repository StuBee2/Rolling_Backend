package com.stubee.rollingadapter.web.member;

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
    @GetMapping("/my")
    @ResponseStatus(OK)
    public Member getMy() {
        return queryMemberUseCase.getMy();
    }

    @Operation(description = "MemberId로 Member 정보 조회")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Member getMemberById(@PathVariable("id") UUID memberId) {
        return queryMemberUseCase.getMemberById(memberId);
    }

}