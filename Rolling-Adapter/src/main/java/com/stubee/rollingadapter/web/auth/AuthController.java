package com.stubee.rollingadapter.web.auth;

import com.stubee.rollingcore.domain.auth.dto.response.RefreshTokenResponse;
import com.stubee.rollingapplication.domain.auth.port.api.RefreshTokenUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Auth", description = "Auth Api")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenUseCase refreshTokenUseCase;

    @Operation(description = "Access Token 재발급")
    @PostMapping("/refresh")
    @ResponseStatus(CREATED)
    public RefreshTokenResponse refresh(final @RequestBody String refreshToken) {
        return refreshTokenUseCase.refresh(refreshToken);
    }

}