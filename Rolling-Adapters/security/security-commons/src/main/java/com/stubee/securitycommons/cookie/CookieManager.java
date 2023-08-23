package com.stubee.securitycommons.cookie;

import jakarta.servlet.http.Cookie;

import java.util.Optional;

public interface CookieManager {

    Optional<Cookie> getCookie(String name);

    void addCookie(Cookie cookie);

    void deleteCookie(String name);

    <T> void addSerializedCookie(String name, T object, int maxAge);

    <T> Optional<T> getDeserializedCookie(String name, Class<T> cls);

}