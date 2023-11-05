package com.stubee.applicationcommons.model.response;

import com.stubee.rollingdomains.common.model.BaseId;

public record TSID(
        String id
) {
    public static TSID of(BaseId id) {
        return new TSID(id.getId().toString());
    }

    public static TSID of(Long id) {
        return new TSID(id.toString());
    }
}