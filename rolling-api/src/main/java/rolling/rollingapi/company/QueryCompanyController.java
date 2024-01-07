package rolling.rollingapi.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rolling.application.common.response.PageDataResponse;
import rolling.application.company.interactor.query.*;
import rolling.domain.common.model.PageRequest;

import java.util.List;

@Tag(name = "Query Company", description = "Query Company API")
@RestController
@RequestMapping(value = "/company")
@RequiredArgsConstructor
public class QueryCompanyController {

    private final QueryCompanyInfoUseCase queryCompanyInfoUseCase;
    private final SearchCompanyByNameUseCase searchCompanyByNameUseCase;
    private final QueryAllCompanyUseCase queryAllCompanyUseCase;
    private final QueryTop10CompanyUseCase queryTop10CompanyUseCase;

    @Operation(description = "Company id로 Company 정보 조회")
    @GetMapping("/info/{id}")
    public CompanyQueryResponse getInfo(final @PathVariable("id") Long companyId) {
        return queryCompanyInfoUseCase.query(companyId);
    }

    @Operation(description = "Company Name으로 Company 검색")
    @GetMapping("/search")
    public PageDataResponse<List<CompanyResponse>> searchByName(final @RequestParam(name = "name") String companyName,
                                                                final @ModelAttribute PageRequest pageRequest) {
        return searchCompanyByNameUseCase.query(companyName, pageRequest);
    }

    @Operation(description = "모든 Company List 조회 (MEMBER & ADMIN)")
    @GetMapping("/list/all")
    public PageDataResponse<List<CompanyResponse>> getAll(final @ModelAttribute PageRequest pageRequest) {
        return queryAllCompanyUseCase.query(pageRequest);
    }

    @Operation(description = "Company Top 10 By Grade (total, salary-benefits, balance, culture, career)")
    @GetMapping("/rank/{gradeType}")
    public List<CompanyResponse> getByGrade(final @PathVariable String gradeType) {
        return queryTop10CompanyUseCase.query(gradeType);
    }

}