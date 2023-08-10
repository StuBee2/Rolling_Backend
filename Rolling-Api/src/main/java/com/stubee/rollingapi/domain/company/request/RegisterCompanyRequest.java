package com.stubee.rollingapi.domain.company.request;

import com.stubee.rollingusecases.domain.company.commands.RegisterCompanyCommand;
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