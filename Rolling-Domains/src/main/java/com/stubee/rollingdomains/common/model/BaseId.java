package com.stubee.rollingdomains.common.model;

import com.stubee.rollingdomains.common.error.exception.NotMatchedIdException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public abstract class BaseId {

    private final Long id;

    public void isEqual(BaseId baseId) {
        if(!this.equals(baseId)) {
            throw NotMatchedIdException.EXCEPTION;
        }
    }

}