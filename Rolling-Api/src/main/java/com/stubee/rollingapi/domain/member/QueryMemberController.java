package com.stubee.rollingapi.domain.member;

import com.stubee.memberapplication.usecases.QueryMemberByIdUseCase;
import com.stubee.memberapplication.usecases.QueryMyInfoUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
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

    private final QueryMyInfoUseCase queryMyInfoUseCase;
    private final QueryMemberByIdUseCase queryMemberByIdUseCase;

    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public Member getMy() {
        return queryMyInfoUseCase.get();
    }

    @Operation(description = "MemberId로 Member 정보 조회")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Member getMemberById(final @PathVariable("id") UUID memberId) {
        return queryMemberByIdUseCase.get(memberId);
    }

}