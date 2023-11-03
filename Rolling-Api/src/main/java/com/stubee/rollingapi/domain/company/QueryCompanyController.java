package com.stubee.rollingapi.domain.company;

import com.stubee.companyapplication.usecases.query.*;
import com.stubee.companyapplication.usecases.query.CompanyQueryResponse;
import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CompanyQueryResponse getInfo(final @PathVariable("id") Long companyId) {
        return queryCompanyInfoByIdUseCase.get(companyId);
    }

    @Operation(description = "Company Name으로 Company 검색")
    @GetMapping("/search")
    public PageDataResponse<List<CompanyResponse>> searchByName(final @RequestParam(name = "name") String companyName,
                                                                final @ModelAttribute PageRequest pageRequest) {
        return searchCompanyListByNameUseCase.get(companyName, pageRequest);
    }

    @Operation(description = "모든 Company List 조회 (ADMIN)")
    @GetMapping("/list/all")
    public PageDataResponse<List<CompanyResponse>> getAll(final @ModelAttribute PageRequest pageRequest) {
        return queryAllCompanyListUseCase.get(pageRequest);
    }

    @Operation(description = "Company Top 10 By Grade (total, salary-benefits, balance, culture, career)")
    @GetMapping("/rank/{gradeType}")
    public List<CompanyResponse> getByGrade(final @PathVariable String gradeType) {
        return queryCompanyListByGradesUseCase.get(gradeType);
    }

}