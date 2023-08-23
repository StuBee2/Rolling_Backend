package com.stubee.securitycommons.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CookieManagerImpl implements CookieManager {

    private final CookieSerializer cookieSerializer;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public Optional<Cookie> getCookie(final String name) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies ->
                        Arrays.stream(cookies)
                                .filter(cookie -> cookie.getName().equals(name))
                                .findFirst());
    }

    public void addCookie(final Cookie cookie) {
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public void deleteCookie(final String name) {
        Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
                .filter(cookie -> cookie.getName().equals(name))
                .forEach(cookie -> {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }

    public <T> void addSerializedCookie(final String name, final T object, final int expire) {
        String cookieValue = cookieSerializer.serialize(object);
        Cookie cookie = new Cookie(name, cookieValue);
        cookie.setMaxAge(expire);
        addCookie(cookie);
    }

    public <T> Optional<T> getDeserializedCookie(final String name, final Class<T> cls) {
        return getCookie(name)
                .map(Cookie::getValue)
                .map(cookieValue -> cookieSerializer.deserialize(cookieValue, cls));
    }

}