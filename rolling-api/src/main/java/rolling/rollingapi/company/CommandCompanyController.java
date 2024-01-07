package rolling.rollingapi.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rolling.application.company.interactor.command.*;
import rolling.domain.common.model.TSID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Tag(name = "Command Company", description = "Command Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class CommandCompanyController {

    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final ModifyCompanyUseCase modifyCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    @Operation(description = "Company 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public TSID register(final @RequestBody @Validated RegisterCompanyRequest request) {
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

    @Operation(description = "Company 수정")
    @PatchMapping("/{companyId}")
    public void modify(final @RequestBody @Validated ModifyCompanyRequest request, final @PathVariable Long companyId) {
        modifyCompanyUseCase.modify(request.toCommand(companyId));
    }

    @Operation(description = "Company 삭제 (ADMIN)")
    @DeleteMapping("/{companyId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(final @PathVariable @NotNull Long companyId) {
        deleteCompanyUseCase.delete(DeleteCompanyCommand.toCommand(companyId));
    }

}