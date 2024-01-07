package rolling.rollingapi.story;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rolling.application.common.response.PageDataResponse;
import rolling.application.story.interactor.query.*;
import rolling.domain.common.model.PageRequest;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Query Story", description = "Query Story API")
@RestController
@RequestMapping(value = "/story")
@RequiredArgsConstructor
public class QueryStoryController {

    private final QueryStoryInfoUseCase queryStoryInfoUseCase;
    private final QueryMyStoryStatusUseCase queryMyStoryStatusUseCase;
    private final QueryStoryByMemberUseCase queryStoryByMemberUseCase;
    private final QueryStoryByCompanyUseCase queryStoryByCompanyUseCase;

    @Operation(description = "id로 Review 단건 조회")
    @GetMapping("/info/{id}")
    @ResponseStatus(OK)
    public StoryQueryByCompanyResponse getInfo(@PathVariable("id") Long reviewId) {
        return queryStoryInfoUseCase.query(reviewId);
    }

    @Operation(description = "내 Story Status 조회")
    @GetMapping("/my/status")
    @ResponseStatus(OK)
    public StoryStatusResponse getMyStatus() {
        return queryMyStoryStatusUseCase.query();
    }

    @Operation(description = "내가 쓴 Story List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByMemberResponse>> getMy(@ModelAttribute PageRequest pageRequest) {
        return queryStoryByMemberUseCase.query(pageRequest);
    }

    @Operation(description = "Member Id로 Story List 조회")
    @GetMapping("/list/member/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByMemberResponse>> getReviewByMember(@PathVariable("id") Long memberId,
                                                                                @ModelAttribute PageRequest pageRequest) {
        return queryStoryByMemberUseCase.query(memberId, pageRequest);
    }

    @Operation(description = "Company Id로 Story List 조회")
    @GetMapping("/list/company/{id}")
    @ResponseStatus(OK)
    public PageDataResponse<List<StoryQueryByCompanyResponse>> getReviewByCompany(@PathVariable("id") Long companyID,
                                                                                  @ModelAttribute PageRequest pageRequest) {
        return queryStoryByCompanyUseCase.query(companyID, pageRequest);
    }

}