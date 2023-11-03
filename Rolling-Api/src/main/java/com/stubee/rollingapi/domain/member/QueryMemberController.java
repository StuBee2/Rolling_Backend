package com.stubee.rollingapi.domain.member;

import com.stubee.memberapplication.usecases.query.MemberResponse;
import com.stubee.memberapplication.usecases.query.QueryMemberByIdUseCase;
import com.stubee.memberapplication.usecases.query.QueryMyInfoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Query Member", description = "Query Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class QueryMemberController {

    private final QueryMyInfoUseCase queryMyInfoUseCase;
    private final QueryMemberByIdUseCase queryMemberByIdUseCase;

    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public MemberResponse getMy() {
        return queryMyInfoUseCase.get();
    }

    @Operation(description = "MemberId로 Member 정보 조회")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public MemberResponse getMemberById(final @PathVariable("id") Long memberId) {
        return queryMemberByIdUseCase.get(memberId);
    }

}