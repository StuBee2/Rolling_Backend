package com.stubee.rollingadapter.web.company;

import com.stubee.rollingadapter.web.company.request.RegisterCompanyRequest;
import com.stubee.rollingapplication.domain.company.port.api.DeleteCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.api.RegisterCompanyUseCase;
import com.stubee.rollingcore.domain.company.command.DeleteCompanyCommand;
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

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Company register(@RequestBody @Validated RegisterCompanyRequest request) {
        return registerCompanyUseCase.register(request.toCommand());
    }

    @Operation(description = "Company 삭제")
    @DeleteMapping("/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID companyId) {
        deleteCompanyUseCase.delete(DeleteCompanyCommand.toCommand(companyId));
    }

}