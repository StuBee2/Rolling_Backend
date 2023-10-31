package com.stubee.securitycommons.utils;

import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public abstract class CookieSerializer {

    public static String serialize(final Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static  <T> T deserialize(final String cookieValue, final Class<T> cls) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(Base64.getUrlDecoder().decode(cookieValue)))) {
            return cls.cast(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}