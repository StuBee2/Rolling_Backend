package com.stubee.rollingdomains.common.model;

import com.stubee.rollingdomains.common.exception.NotMatchedIdException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public abstract class BaseId {

    private final UUID id;

    public void isEqual(BaseId baseId) {
        if(!this.equals(baseId)) {
            throw NotMatchedIdException.EXCEPTION;
        }
    }

}