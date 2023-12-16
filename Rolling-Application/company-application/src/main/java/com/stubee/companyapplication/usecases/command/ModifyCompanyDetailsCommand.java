package com.stubee.companyapplication.usecases.command;

public record ModifyCompanyDetailsCommand(
        Long id,
        String name,
        String description,
        String companyAddress,
        String companyAddressEtc,
        String url,
        Integer rgb) {}