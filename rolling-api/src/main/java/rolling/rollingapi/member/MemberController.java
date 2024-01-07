package rolling.rollingapi.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rolling.application.member.interactor.command.ModifyNicknameUseCase;
import rolling.application.member.interactor.query.MemberResponse;
import rolling.application.member.interactor.query.QueryMemberInfoUseCase;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Command Member", description = "Command Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final ModifyNicknameUseCase modifyNicknameUseCase;
    private final QueryMemberInfoUseCase queryMemberInfoUseCase;

    @Operation(description = "Member NickName 수정")
    @PatchMapping("/nickName")
    @ResponseStatus(NO_CONTENT)
    public void modify(final @RequestBody @Validated ModifyNicknameRequest request) {
        modifyNicknameUseCase.modify(request.toCommand());
    }

    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    public MemberResponse getMy() {
        return queryMemberInfoUseCase.query();
    }

    @Operation(description = "MemberId로 Member 정보 조회")
    @GetMapping("/{id}")
    public MemberResponse getMemberById(final @PathVariable("id") Long memberId) {
        return queryMemberInfoUseCase.query(memberId);
    }

}