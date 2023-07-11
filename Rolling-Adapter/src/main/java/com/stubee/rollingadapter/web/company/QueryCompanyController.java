package com.stubee.rollingadapter.web.company;

import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyByMemberUseCase;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
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
    private final QueryCompanyByMemberUseCase queryCompanyByMemberUseCase;

    @Operation(description = "Company id로 Company 정보 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public CompanyQueryResponse getInfo(final @PathVariable("id") UUID companyId) {
        return queryCompanyUseCase.getInfoById(companyId);
    }

    @Operation(description = "Company Name으로 Company 검색")
    @GetMapping("/search")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> searchByName(final @RequestParam(name = "name") String companyName,
                                                        @ModelAttribute PageRequest pageRequest) {
        return queryCompanyUseCase.getListByNameContaining(companyName, pageRequest);
    }

    @Operation(description = "모든 Company List 조회")
    @GetMapping("/list")
    @ResponseStatus(OK)
    public List<Company> getAll(@ModelAttribute PageRequest pageRequest) {
        return queryCompanyUseCase.getList(pageRequest);
    }

    @Operation(description = "내가 등록한 Company List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> getMy(@ModelAttribute PageRequest pageRequest) {
        return queryCompanyByMemberUseCase.getMy(pageRequest);
    }

    @Operation(description = "Member Id로 Company List 조회")
    @GetMapping("/list/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> getByMember(final @PathVariable("id") UUID memberId,
                                     @ModelAttribute PageRequest pageRequest) {
        return queryCompanyByMemberUseCase.getByMemberId(memberId, pageRequest);
    }

    @Operation(description = "Company TotalGrade Top 10")
    @GetMapping("/rank/total")
    @ResponseStatus(OK)
    public List<Company> getByTotalGrade() {
        return queryCompanyUseCase.getByTotalGrade();
    }

    @Operation(description = "Company Salary And Benefits Top 10")
    @GetMapping("/rank/salary-benefits")
    @ResponseStatus(OK)
    public List<Company> getBySalaryAndBenefits() {
        return queryCompanyUseCase.getBySalaryAndBenefits();
    }

    @Operation(description = "Company Work-Life Balance Top 10")
    @GetMapping("/rank/balance")
    @ResponseStatus(OK)
    public List<Company> getByWorkLifeBalance() {
        return queryCompanyUseCase.getByWorkLifeBalance();
    }

    @Operation(description = "Company Organizational Culture Top 10")
    @GetMapping("/rank/culture")
    @ResponseStatus(OK)
    public List<Company> getByOrganizationalCulture() {
        return queryCompanyUseCase.getByOrganizationalCulture();
    }

    @Operation(description = "Company Career Advancement Top 10")
    @GetMapping("/rank/career")
    @ResponseStatus(OK)
    public List<Company> getByCareerAdvancement() {
        return queryCompanyUseCase.getByCareerAdvancement();
    }

}