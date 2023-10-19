package com.stubee.rollingapi.domain.story;

import com.stubee.applicationcommons.dtos.response.PageDataResponse;
import com.stubee.reviewapplication.usecases.query.*;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Query Story", description = "Query Story API")
@RestController
@RequestMapping(value = "/story")
@RequiredArgsConstructor
public class QueryStoryController {

    private final QueryStoryInfoByIdUseCase queryStoryInfoByIdUseCase;
    private final QueryMyStoryListUseCase queryMyStoryListUseCase;
    private final QueryMyStoryStatusUseCase queryMyStoryStatusUseCase;
    private final QueryStoryListByMemberUseCase queryStoryListByMemberUseCase;
    private final QueryStoryInfoListByCompanyUseCase queryStoryInfoListByCompanyUseCase;

    @Operation(description = "id로 Review 단건 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public StoryQueryByCompanyResponse getInfo(@PathVariable("id") Long reviewId) {
        return queryStoryInfoByIdUseCase.get(reviewId);
    }

    @Operation(description = "내가 쓴 Story List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByMemberResponse>> getMy(@ModelAttribute PageRequest pageRequest) {
        return queryMyStoryListUseCase.get(pageRequest);
    }

    @Operation(description = "내 Story Status 조회")
    @GetMapping("/my/status")
    @ResponseStatus(OK)
    public StoryStatusResponse getMyStatus() {
        return queryMyStoryStatusUseCase.get();
    }

    @Operation(description = "Member Id로 Story List 조회")
    @GetMapping("/list/member/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByMemberResponse>> getReviewByMember(@PathVariable("id") Long memberId,
                                                                                @ModelAttribute PageRequest pageRequest) {
        return queryStoryListByMemberUseCase.get(memberId, pageRequest);
    }

    @Operation(description = "Company Id로 Story List 조회")
    @GetMapping("/list/company/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByCompanyResponse>> getReviewByCompany(@PathVariable("id") Long companyID,
                                                                                  @ModelAttribute PageRequest pageRequest) {
        return queryStoryInfoListByCompanyUseCase.get(companyID, pageRequest);
    }

}