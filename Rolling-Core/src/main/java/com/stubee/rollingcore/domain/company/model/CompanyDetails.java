package com.stubee.rollingcore.domain.company.model;

import lombok.Builder;

@Builder
public record CompanyDetails(
        String name,
        Address address,
        String description,
        String imgUrl) {}