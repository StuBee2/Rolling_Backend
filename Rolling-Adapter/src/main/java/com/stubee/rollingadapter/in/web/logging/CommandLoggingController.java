package com.stubee.rollingadapter.in.web.logging;

import com.stubee.rollingadapter.in.web.logging.request.CreateLoggingRequest;
import com.stubee.rollingapplication.domain.logging.port.api.CommandLoggingUseCase;
import com.stubee.rollingcore.domain.logging.model.Logging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Logging", description = "Command Logging API")
@RestController
@RequestMapping(value = "/logging")
@RequiredArgsConstructor
public class CommandLoggingController {

    private final CommandLoggingUseCase commandLoggingUseCase;

    @Operation(description = "logging 생성")
    @PostMapping
    @ResponseStatus(CREATED)
    public Logging create(final @RequestBody @Validated CreateLoggingRequest request) {
        return commandLoggingUseCase.create(request.toCommand());
    }

}