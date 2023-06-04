package com.stubee.rollingadapter.in.web.company;

import com.stubee.rollingcore.common.dto.PageDataResponse;
import com.stubee.rollingcore.common.dto.PageDto;
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
                                                        @ModelAttribute PageDto pageDto) {
        return queryCompanyUseCase.getListByNameContaining(companyName, pageDto);
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
    public PageDataResponse<List<Company>> getMy(@ModelAttribute PageDto pageDto) {
        return queryCompanyUseCase.getMy(pageDto);
    }

    @Operation(description = "Member Id로 Company List 조회")
    @GetMapping("/list/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<Company>> getByMember(final @PathVariable("id") UUID memberId,
                                     @ModelAttribute PageDto pageDto) {
        return queryCompanyUseCase.getByMemberId(memberId, pageDto);
    }

    @Operation(description = "회사 TotalGrade Top 10")
    @GetMapping("/rank/total")
    @ResponseStatus(OK)
    public List<Company> getByTotalGrade() {
        return queryCompanyUseCase.getByTotalGrade();
    }

    @Operation(description = "회사 SalaryGrade Top 10")
    @GetMapping("/rank/salary")
    @ResponseStatus(OK)
    public List<Company> getBySalaryGrade() {
        return queryCompanyUseCase.getBySalaryGrade();
    }

    @Operation(description = "회사 WelfareGrade Top 10")
    @GetMapping("/rank/welfare")
    @ResponseStatus(OK)
    public List<Company> getByWelfareGrade() {
        return queryCompanyUseCase.getByWelfareGrade();
    }

    @Operation(description = "회사 BalanceGrade Top 10")
    @GetMapping("/rank/balance")
    @ResponseStatus(OK)
    public List<Company> getByBalanceGrade() {
        return queryCompanyUseCase.getByBalanceGrade();
    }

}