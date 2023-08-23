package com.stubee.rollingdomains.common.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class BaseId {

    private final UUID id;

    public static BaseId create(final UUID id) {
        return new BaseId(id);
    }

}