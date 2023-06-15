package com.stubee.rollingapplication.domain.company.command;

import lombok.Builder;

@Builder
public record RegisterCompanyCommand(
        String name,
        String address,
        String description,
        String imgUrl) {}