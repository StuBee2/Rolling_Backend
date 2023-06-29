package com.stubee.rollinginfrastructure.common.feign.naver.dto;

import java.util.List;

public record NaverNewsResponse(
        Integer total,
        Integer start,
        Integer display,
        List<NaverNews> items) {
    private record NaverNews(
            String title,
            String originallink,
            String link,
            String description,
            String pubDate) {}
}