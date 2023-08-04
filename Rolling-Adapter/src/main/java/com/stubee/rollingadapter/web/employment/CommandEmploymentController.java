package com.stubee.rollingadapter.web.employment;

import com.stubee.rollingadapter.web.employment.request.RegisterEmploymentRequest;
import com.stubee.rollingapplication.domain.employment.port.api.command.RegisterEmploymentUseCase;
import com.stubee.rollingcore.domain.employment.model.Employment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Employment", description = "Command Employment API")
@RestController
@RequestMapping(value = "/employment")
@RequiredArgsConstructor
public class CommandEmploymentController {

    private final RegisterEmploymentUseCase registerEmploymentUseCase;

    @Operation(description = "Employment 추가 (내 회사 추가)")
    @PostMapping
    @ResponseStatus(CREATED)
    public Employment register(final @RequestBody RegisterEmploymentRequest request) {
        return registerEmploymentUseCase.register(request.toCommand());
    }

}