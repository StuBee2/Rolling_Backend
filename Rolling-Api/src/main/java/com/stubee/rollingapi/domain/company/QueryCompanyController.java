package com.stubee.rollingapi.domain.company;

import com.stubee.companyapplication.usecases.query.*;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Query Company", description = "Query Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class QueryCompanyController {

    private final QueryAllCompanyListUseCase queryAllCompanyListUseCase;
    private final QueryCompanyInfoByIdUseCase queryCompanyInfoByIdUseCase;
    private final SearchCompanyListByNameUseCase searchCompanyListByNameUseCase;
    private final QueryCompanyListByGradesUseCase queryCompanyListByGradesUseCase;

    @Operation(description = "Company id로 Company 정보 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public CompanyQueryResponse getInfo(final @PathVariable("id") Long companyId) {
        return queryCompanyInfoByIdUseCase.get(companyId);
    }

    @Operation(description = "Company Name으로 Company 검색")
    @GetMapping("/search")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> searchByName(final @RequestParam(name = "name") String companyName,
                                                        final @ModelAttribute PageRequest pageRequest) {
        return searchCompanyListByNameUseCase.get(companyName, pageRequest);
    }

    @Operation(description = "모든 Company List 조회 (ADMIN)")
    @GetMapping("/list/all")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> getAll(final @ModelAttribute PageRequest pageRequest) {
        return queryAllCompanyListUseCase.get(pageRequest);
    }

    @Operation(description = "Company TotalGrade Top 10")
    @GetMapping("/rank/total")
    @ResponseStatus(OK)
    public List<Company> getByTotalGrade() {
        return queryCompanyListByGradesUseCase.getByTotalGrade();
    }

    @Operation(description = "Company Salary And Benefits Top 10")
    @GetMapping("/rank/salary-benefits")
    @ResponseStatus(OK)
    public List<Company> getBySalaryAndBenefits() {
        return queryCompanyListByGradesUseCase.getBySalaryAndBenefits();
    }

    @Operation(description = "Company Work-Life Balance Top 10")
    @GetMapping("/rank/balance")
    @ResponseStatus(OK)
    public List<Company> getByWorkLifeBalance() {
        return queryCompanyListByGradesUseCase.getByWorkLifeBalance();
    }

    @Operation(description = "Company Organizational Culture Top 10")
    @GetMapping("/rank/culture")
    @ResponseStatus(OK)
    public List<Company> getByOrganizationalCulture() {
        return queryCompanyListByGradesUseCase.getByOrganizationalCulture();
    }

    @Operation(description = "Company Career Advancement Top 10")
    @GetMapping("/rank/career")
    @ResponseStatus(OK)
    public List<Company> getByCareerAdvancement() {
        return queryCompanyListByGradesUseCase.getByCareerAdvancement();
    }

}