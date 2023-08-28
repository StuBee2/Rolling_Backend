package com.stubee.rollingapi.domain.logging;

import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingapi.domain.logging.request.PileUpLoggingRequest;
import com.stubee.rollingdomains.domain.logging.model.Logging;
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

    private final PileUpLoggingUseCase pileUpLoggingUseCase;

    @Operation(description = "logging 생성")
    @PostMapping
    @ResponseStatus(CREATED)
    public Logging pileUp(final @RequestBody @Validated PileUpLoggingRequest request) {
        return pileUpLoggingUseCase.pileUp(request.toCommand());
    }

}