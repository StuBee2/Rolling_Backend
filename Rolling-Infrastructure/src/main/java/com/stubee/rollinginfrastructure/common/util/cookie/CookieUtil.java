package com.stubee.rollinginfrastructure.common.util.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CookieUtil implements CookieManager {

    private final CookieSerializer cookieSerializer;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Override
    public Optional<Cookie> getCookie(String name) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies ->
                        Arrays.stream(cookies)
                                .filter(cookie -> cookie.getName().equals(name))
                                .findFirst()
                );
    }

    @Override
    public void addCookie(Cookie cookie) {
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public void deleteCookie(String name) {
        Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
                .filter(cookie -> cookie.getName().equals(name))
                .forEach(cookie -> {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }

    @Override
    public <T> void addSerializedCookie(String name, T object, int expire) {
        String cookieValue = cookieSerializer.serialize(object);
        Cookie cookie = new Cookie(name, cookieValue);
        cookie.setMaxAge(expire);
        addCookie(cookie);
    }

    @Override
    public <T> Optional<T> getDeserializedCookie(String name, Class<T> cls) {
        return getCookie(name)
                .map(Cookie::getValue)
                .map(cookieValue -> cookieSerializer.deserialize(cookieValue, cls));
    }

}