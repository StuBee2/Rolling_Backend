package com.stubee.rollingapi.domain.review;

import com.stubee.reviewapplication.usecases.query.QueryMyReviewListUseCase;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoByIdUseCase;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoListByCompanyUseCase;
import com.stubee.reviewapplication.usecases.query.QueryReviewListByMemberUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.response.ReviewQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Query Review", description = "Query Review API")
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class QueryReviewController {

    private final QueryReviewInfoByIdUseCase queryReviewInfoByIdUseCase;
    private final QueryMyReviewListUseCase queryMyReviewListUseCase;
    private final QueryReviewListByMemberUseCase queryReviewListByMemberUseCase;
    private final QueryReviewInfoListByCompanyUseCase queryReviewInfoListByCompanyUseCase;

    @Operation(description = "id로 Review 단건 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public ReviewInfoResponse getInfo(@PathVariable("id") UUID reviewId) {
        return queryReviewInfoByIdUseCase.get(reviewId);
    }

    @Operation(description = "내가 쓴 Review List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public PageDataResponse<List<ReviewQueryResponse>> getMy(@ModelAttribute PageRequest pageRequest) {
        return queryMyReviewListUseCase.get(pageRequest);
    }

    @Operation(description = "Member Id로 Review List 조회")
    @GetMapping("/list/member/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<ReviewQueryResponse>> getReviewByMember(@PathVariable("id") UUID memberId,
                                                       @ModelAttribute PageRequest pageRequest) {
        return queryReviewListByMemberUseCase.get(memberId, pageRequest);
    }

    @Operation(description = "Company Id로 Review List 조회")
    @GetMapping("/list/company/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<ReviewInfoResponse>> getReviewByCompany(@PathVariable("id") UUID companyID,
                                                                        @ModelAttribute PageRequest pageRequest) {
        return queryReviewInfoListByCompanyUseCase.get(companyID, pageRequest);
    }

}