package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

public record CorporationDetails(
        String position,
        String welfare,
        String commuteTime,
        String meal,
        String pros,
        String cons,
        String etc
) {
    @Builder
    public CorporationDetails {
        Assert.notNull(position, "Position must not be null");
        Assert.notNull(welfare, "Welfare must not be null");
        Assert.notNull(pros, "Pros must not be null");
        Assert.notNull(cons, "Cons must not be null");
    }
}