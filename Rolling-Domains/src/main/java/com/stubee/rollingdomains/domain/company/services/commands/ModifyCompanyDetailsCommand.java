package com.stubee.rollingdomains.domain.company.services.commands;


public record ModifyCompanyDetailsCommand(
        Long id,
        String name,
        String description,
        String companyAddress,
        String companyAddressEtc,
        String url,
        Integer rgb) {}