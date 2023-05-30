package com.stubee.rollingcore.domain.company.dto.command;

import lombok.Builder;

@Builder
public record RegisterCompanyCommand(
        String name,
        String address,
        String description,
        String imgUrl) {}