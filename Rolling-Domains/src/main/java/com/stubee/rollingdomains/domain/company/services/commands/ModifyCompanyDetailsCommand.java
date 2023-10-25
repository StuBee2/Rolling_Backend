package com.stubee.rollingdomains.domain.company.services.commands;


public record ModifyCompanyDetailsCommand(
        Long id,
        String description,
        String companyAddress,
        String url,
        Integer rgb) {}