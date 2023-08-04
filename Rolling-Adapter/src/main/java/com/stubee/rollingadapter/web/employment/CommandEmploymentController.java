package com.stubee.rollingadapter.web.employment;

import com.stubee.rollingadapter.web.employment.request.RegisterEmploymentRequest;
import com.stubee.rollingapplication.domain.employment.port.api.command.RegisterEmploymentUseCase;
import com.stubee.rollingcore.domain.employment.model.Employment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/employment")
@RequiredArgsConstructor
public class CommandEmploymentController {

    private final RegisterEmploymentUseCase registerEmploymentUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public Employment register(final @RequestBody RegisterEmploymentRequest request) {
        return registerEmploymentUseCase.register(request.toCommand());
    }

}