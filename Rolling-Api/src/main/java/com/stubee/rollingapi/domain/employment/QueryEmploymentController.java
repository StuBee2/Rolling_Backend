package com.stubee.rollingapi.domain.employment;

import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Query Employment", description = "Query Employment API")
@RestController
@RequestMapping(value = "/employment")
@RequiredArgsConstructor
public class QueryEmploymentController {

    private final QueryMyEmploymentInfoListUseCase queryMyEmploymentInfoListUseCase;

    @Operation(description = "내 Employment List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public List<EmploymentQueryResponse> getMy() {
        return queryMyEmploymentInfoListUseCase.get();
    }

}