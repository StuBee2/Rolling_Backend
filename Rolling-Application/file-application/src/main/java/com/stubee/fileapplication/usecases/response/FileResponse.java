package com.stubee.fileapplication.usecases.response;

public record FileResponse(
        String url,
        Integer rgb
) {
    public static FileResponse of(String url, Integer rgb) {
        return new FileResponse(url, rgb);
    }
}