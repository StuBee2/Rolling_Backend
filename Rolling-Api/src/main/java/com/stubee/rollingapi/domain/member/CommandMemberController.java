package com.stubee.rollingapi.domain.member;

import com.stubee.memberapplication.usecases.command.ModifyNicknameUseCase;
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

    private final ModifyNicknameUseCase modifyNicknameUseCase;

    @Operation(description = "Member NickName 수정")
    @PatchMapping("/nickName")
    @ResponseStatus(NO_CONTENT)
    public void changeNickname(final @RequestBody @Validated ModifyNicknameRequest request) {
        modifyNicknameUseCase.modify(request.toCommand());
    }

}