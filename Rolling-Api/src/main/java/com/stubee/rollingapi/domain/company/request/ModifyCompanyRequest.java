package com.stubee.rollingapi.domain.company.request;

import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;
import jakarta.validation.constraints.NotBlank;

public record ModifyCompanyRequest(
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