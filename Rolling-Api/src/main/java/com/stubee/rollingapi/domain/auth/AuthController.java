package com.stubee.rollingapi.domain.auth;

import com.stubee.authapplication.usecases.CertifyAlumniUseCase;
import com.stubee.authapplication.usecases.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
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
    private final CertifyAlumniUseCase certifyAlumniUseCase;

    @Operation(description = "Access Token 재발급")
    @PostMapping("/refresh")
    @ResponseStatus(OK)
    public RefreshTokenResponse refresh(final @RequestBody RefreshTokenRequest request) {
        return refreshTokenUseCase.refresh(request.refreshToken());
    }

    @Operation(description = "졸업생/재학생 인증")
    @PatchMapping("/certify")
    public void certify(final @RequestBody CertifyAlumniRequest request) {
        certifyAlumniUseCase.certify(request.housemaster());
    }

}