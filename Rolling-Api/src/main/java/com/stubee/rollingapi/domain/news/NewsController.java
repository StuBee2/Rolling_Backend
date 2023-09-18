package com.stubee.rollingapi.domain.news;

import com.stubee.newsapplication.usecases.NewsUseCase;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<?> getNewsByCompanyName(final @PathVariable String companyName,
                                        final @ModelAttribute PageRequest pageRequest) {
        return newsUseCase.getNewsByCompanyName(companyName, pageRequest);
    }

}