package com.stubee.rollingapi.domain.company;

import com.stubee.companyapplication.usecases.command.ModifyCompanyDetailsCommand;
import jakarta.validation.constraints.NotBlank;

record ModifyCompanyRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String address,
        String addressEtc,
        String imgUrl,
        Integer rgb) {
    public ModifyCompanyDetailsCommand toCommand(Long id) {
        return new ModifyCompanyDetailsCommand(id, name, description, address, addressEtc, imgUrl, rgb);
    }
}