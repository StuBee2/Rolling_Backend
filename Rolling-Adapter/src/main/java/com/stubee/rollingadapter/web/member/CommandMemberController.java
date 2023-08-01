package com.stubee.rollingadapter.web.member;

import com.stubee.rollingadapter.web.member.request.UpdateNickNameRequest;
import com.stubee.rollingapplication.domain.member.port.api.UpdateNicknameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Member", description = "Command Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class CommandMemberController {

    private final UpdateNicknameUseCase updateNicknameUseCase;

    @Operation(description = "Member NickName 수정")
    @PatchMapping("/nickName")
    @ResponseStatus(NO_CONTENT)
    public void updateNickName(final @RequestBody @Validated UpdateNickNameRequest request) {
        updateNicknameUseCase.update(request.toCommand());
    }

}