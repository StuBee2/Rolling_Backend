package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

public record CorporationDetails(
        String welfare, //회사 복지
        String commuteTime, //회사 출퇴근 시간
        String meal, //식사
        String advantages, //장점
        String disAdvantages //단점
) {
    @Builder
    public CorporationDetails {
        Assert.notNull(welfare, "Welfare must not be null");
        Assert.notNull(commuteTime, "CommuteTime must not be null");
        Assert.notNull(meal, "Meal must not be null");
        Assert.notNull(advantages, "Advantages must not be null");
        Assert.notNull(disAdvantages, "DisAdvantages must not be null");
    }
}