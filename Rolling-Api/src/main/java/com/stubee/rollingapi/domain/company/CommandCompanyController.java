package com.stubee.rollingapi.domain.company;

import com.stubee.rollingdomains.domain.company.services.commands.ChangeCompanyStatusCommand;
import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;
import com.stubee.companyapplication.usecases.command.ChangeCompanyStatusUseCase;
import com.stubee.companyapplication.usecases.command.DeleteCompanyUseCase;
import com.stubee.companyapplication.usecases.command.RegisterCompanyUseCase;
import com.stubee.rollingapi.domain.company.request.RegisterCompanyRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
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
    private final ChangeCompanyStatusUseCase changeCompanyStatusUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Company register(final @RequestBody @Validated RegisterCompanyRequest request) {
        return registerCompanyUseCase.register(request.toCommand());
    }

    @Operation(description = "Company 수락 (ADMIN)")
    @PatchMapping("/accept/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void accept(final @PathVariable @NotNull UUID companyId) {
        changeCompanyStatusUseCase.change(ChangeCompanyStatusCommand.accept(companyId));
    }

    @Operation(description = "Company 거절 (ADMIN)")
    @PatchMapping("/deny/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void deny(final @PathVariable @NotNull UUID companyId) {
        changeCompanyStatusUseCase.change(ChangeCompanyStatusCommand.deny(companyId));
    }

    @Operation(description = "Company 삭제")
    @DeleteMapping("/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable UUID companyId) {
        deleteCompanyUseCase.delete(DeleteCompanyCommand.toCommand(companyId));
    }

}