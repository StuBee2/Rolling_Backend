package com.stubee.securitycommons.cookie;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

@Component
public class CookieSerializer {

    public String serialize(final Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public <T> T deserialize(final String cookieValue, final Class<T> cls) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(Base64.getUrlDecoder().decode(cookieValue)))) {
            return cls.cast(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}