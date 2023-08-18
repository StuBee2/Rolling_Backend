package com.stubee.rollingapi.domain.member;

import com.stubee.memberapplication.usecases.ChangeNicknameUseCase;
import com.stubee.rollingapi.domain.member.request.ChangeNicknameRequest;
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

    private final ChangeNicknameUseCase changeNicknameUseCase;

    @Operation(description = "Member NickName 수정")
    @PatchMapping("/nickName")
    @ResponseStatus(NO_CONTENT)
    public void changeNickname(final @RequestBody @Validated ChangeNicknameRequest request) {
        changeNicknameUseCase.update(request.toCommand());
    }

}