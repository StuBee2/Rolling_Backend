package com.stubee.rollingadapter.web.company.request;

import com.stubee.rollingcore.domain.company.command.RegisterCompanyCommand;
import jakarta.validation.constraints.NotBlank;

public record RegisterCompanyRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String description,
        String imgUrl) {
    public RegisterCompanyCommand toCommand() {
        return RegisterCompanyCommand.create(name, address, description, imgUrl);
    }
}