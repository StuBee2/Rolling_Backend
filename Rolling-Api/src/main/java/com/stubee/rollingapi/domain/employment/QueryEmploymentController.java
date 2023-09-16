package com.stubee.rollingapi.domain.employment;

import com.stubee.employmentapplication.usecases.query.QueryEmploymentExistenceUseCase;
import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.employmentapplication.usecases.query.response.EmploymentQueryResponse;
import com.stubee.rollingapi.domain.employment.request.GetEmploymentExistenceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Query Employment", description = "Query Employment API")
@RestController
@RequestMapping(value = "/employment")
@RequiredArgsConstructor
public class QueryEmploymentController {

    private final QueryMyEmploymentInfoListUseCase queryMyEmploymentInfoListUseCase;
    private final QueryEmploymentExistenceUseCase queryEmploymentExistenceUseCase;

    @Operation(description = "내 Employment List 조회")
    @GetMapping("/my")
    public List<EmploymentQueryResponse> getMy() {
        return queryMyEmploymentInfoListUseCase.get();
    }

    @Operation(description = "Employment Existence 확인")
    @GetMapping
    public boolean checkExistence(final GetEmploymentExistenceRequest request) {
        return queryEmploymentExistenceUseCase.get(request.toQuery());
    }

}