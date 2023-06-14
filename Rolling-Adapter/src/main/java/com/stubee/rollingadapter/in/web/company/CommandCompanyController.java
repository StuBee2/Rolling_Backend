package com.stubee.rollingadapter.in.web.company;

import com.stubee.rollingadapter.in.web.company.request.RegisterCompanyRequest;
import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingcore.domain.company.model.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Company", description = "Command Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class CommandCompanyController {

    private final CommandCompanyUseCase commandCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Company register(final @RequestBody @Validated RegisterCompanyRequest request) {
        return commandCompanyUseCase.register(request.toCommand());
    }

    @Operation(description = "Company 삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable("id") UUID companyId) {
        commandCompanyUseCase.delete(companyId);
    }

}