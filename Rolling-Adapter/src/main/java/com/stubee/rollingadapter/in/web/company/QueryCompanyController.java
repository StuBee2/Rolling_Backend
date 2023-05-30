package com.stubee.rollingadapter.in.web.company;

import com.stubee.rollingcore.domain.company.dto.response.CompanyInfoResponse;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingcore.domain.company.model.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Query Company", description = "Query Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class QueryCompanyController {

    private final QueryCompanyUseCase queryCompanyUseCase;

    @Operation(description = "Company id로 Company 정보 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public CompanyInfoResponse getInfo(final @PathVariable("id") UUID companyId) {
        return queryCompanyUseCase.getInfoById(companyId);
    }

    @Operation(description = "Company Name으로 Company 검색")
    @GetMapping("/search")
    @ResponseStatus(OK)
    public List<Company> searchByName(final @RequestParam("name") String companyName) {
        return queryCompanyUseCase.getListByNameContaining(companyName);
    }

    @Operation(description = "모든 Company List 조회")
    @GetMapping("/list")
    @ResponseStatus(OK)
    public List<Company> getAll() {
        return queryCompanyUseCase.getList();
    }

    @Operation(description = "내가 등록한 Company List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public List<Company> getMy() {
        return queryCompanyUseCase.getMy();
    }

    @Operation(description = "Member Id로 Company List 조회")
    @GetMapping("/list/{id}")
    @ResponseStatus(OK)
    public List<Company> getByMember(final @PathVariable("id") UUID memberId) {
        return queryCompanyUseCase.getByMemberId(memberId);
    }

}