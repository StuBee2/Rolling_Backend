package com.stubee.rollingcore.domain.news.dto.response;

public record NewsResponse <T> (
        T data) {
    public static <T> NewsResponse create(T data) {
        return new <T> NewsResponse(data);
    }
}