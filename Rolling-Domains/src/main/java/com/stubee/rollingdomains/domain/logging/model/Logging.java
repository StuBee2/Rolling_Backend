package com.stubee.rollingdomains.domain.logging.model;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.Builder;

import java.time.LocalDateTime;

public record Logging (
        Long id,
        String description,
        String module,
        MemberId memberId,
        LocalDateTime createdAt) {
    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Logging(String description, String module, MemberId memberId) {
        this(null, description, module, memberId, null);
    }

    @Builder(builderClassName = "WithIdBuilder", builderMethodName = "WithIdBuilder")
    public Logging {
    }
}