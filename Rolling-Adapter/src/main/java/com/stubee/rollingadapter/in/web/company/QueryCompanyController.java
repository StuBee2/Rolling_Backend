package com.stubee.rollingadapter.in.web.company;

import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.company.dto.response.CompanyQueryResponse;
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
        return queryCompanyUseCase.getMy(pageRequest);
    }

    @Operation(description = "Member Id로 Company List 조회")
    @GetMapping("/list/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> getByMember(final @PathVariable("id") UUID memberId,
                                     @ModelAttribute PageRequest pageRequest) {
        return queryCompanyUseCase.getByMemberId(memberId, pageRequest);
    }

    @Operation(description = "Company TotalGrade Top 10")
    @GetMapping("/rank/total")
    @ResponseStatus(OK)
    public List<Company> getByTotalGrade() {
        return queryCompanyUseCase.getByTotalGrade();
    }

    @Operation(description = "Company SalaryGrade Top 10")
    @GetMapping("/rank/salary")
    @ResponseStatus(OK)
    public List<Company> getBySalaryGrade() {
        return queryCompanyUseCase.getBySalaryGrade();
    }

    @Operation(description = "Company WelfareGrade Top 10")
    @GetMapping("/rank/welfare")
    @ResponseStatus(OK)
    public List<Company> getByWelfareGrade() {
        return queryCompanyUseCase.getByWelfareGrade();
    }

    @Operation(description = "Company BalanceGrade Top 10")
    @GetMapping("/rank/balance")
    @ResponseStatus(OK)
    public List<Company> getByBalanceGrade() {
        return queryCompanyUseCase.getByBalanceGrade();
    }

}