package com.stubee.rollingapi.domain.company;

import com.stubee.rollingapi.domain.company.request.RegisterCompanyRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingusecases.domain.company.commands.DeleteCompanyCommand;
import com.stubee.rollingusecases.domain.company.usecases.command.DeleteCompanyUseCase;
import com.stubee.rollingusecases.domain.company.usecases.command.RegisterCompanyUseCase;
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

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Company register(final @RequestBody @Validated RegisterCompanyRequest request) {
        return registerCompanyUseCase.register(request.toCommand());
    }

    @Operation(description = "Company 삭제")
    @DeleteMapping("/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable UUID companyId) {
        deleteCompanyUseCase.delete(DeleteCompanyCommand.toCommand(companyId));
    }

}