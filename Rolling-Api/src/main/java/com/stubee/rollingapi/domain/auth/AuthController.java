package com.stubee.rollingapi.domain.auth;

import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;
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
    @ResponseStatus(OK)
    public RefreshTokenResponse refresh(final @RequestBody String refreshToken) {
        return refreshTokenUseCase.refresh(refreshToken);
    }

}