package com.stubee.rollingadapter.web.news;

import com.stubee.rollingapplication.domain.news.port.api.NewsUseCase;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "News", description = "News API")
@RestController
@RequestMapping(value = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsUseCase newsUseCase;

    @Operation(description = "companyName으로 최신 뉴스 기사 조회")
    @GetMapping("/{companyName}")
    @ResponseStatus(OK)
    public CompletableFuture<?> getNewsByCompanyName(final @PathVariable String companyName,
                                                     final @ModelAttribute PageRequest pageRequest) {
        return newsUseCase.getNewsByCompanyName(companyName, pageRequest);
    }

}