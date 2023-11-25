package com.stubee.rollingdomains.logging.model;

import com.stubee.rollingdomains.common.error.Assert;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

import java.time.LocalDateTime;

public record HistoryLogging(
        Long id,
        String description,
        String module,
        MemberId memberId,
        Boolean isAnonymous,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public HistoryLogging(String description, String module, MemberId memberId) {
        this(null, description, module, memberId, memberId==null, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public HistoryLogging {
        Assert.notNull(description, "Description must not be null");
        Assert.notNull(module, "Module must not be null");
        Assert.notNull(isAnonymous, "IsAnonymous must not be null");
    }
}