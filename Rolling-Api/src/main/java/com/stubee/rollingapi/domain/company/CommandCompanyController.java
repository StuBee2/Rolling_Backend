package com.stubee.rollingapi.domain.company;

import com.stubee.companyapplication.usecases.command.ModifyCompanyUseCase;
import com.stubee.rollingapi.domain.company.request.ModifyCompanyRequest;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyStatusCommand;
import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;
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

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Command Company", description = "Command Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class CommandCompanyController {

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final ModifyCompanyUseCase modifyCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Company register(final @RequestBody @Validated RegisterCompanyRequest request) {
        return registerCompanyUseCase.register(request.toCommand());
    }

    @Operation(description = "Company 수락 (ADMIN)")
    @PatchMapping("/accept/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void accept(final @PathVariable @NotNull Long companyId) {
        modifyCompanyUseCase.modify(ModifyCompanyStatusCommand.accept(companyId));
    }

    @Operation(description = "Company 거절 (ADMIN)")
    @PatchMapping("/deny/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void deny(final @PathVariable @NotNull Long companyId) {
        modifyCompanyUseCase.modify(ModifyCompanyStatusCommand.deny(companyId));
    }

    @Operation(description = "Company 삭제 (ADMIN)")
    @DeleteMapping("/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable Long companyId) {
        deleteCompanyUseCase.delete(DeleteCompanyCommand.toCommand(companyId));
    }

    @Operation(description = "Company 수정")
    @PatchMapping("/{companyId}")
    public void modify(final @RequestBody ModifyCompanyRequest request, final @PathVariable Long companyId) {
        modifyCompanyUseCase.modify(request.toCommand(companyId));
    }

}