package com.stubee.rollingadapter.in.web.company.request;

import com.stubee.rollingcore.domain.company.command.RegisterCompanyCommand;
import jakarta.validation.constraints.NotBlank;

public record RegisterCompanyRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String description,
        String imgUrl) {
    public RegisterCompanyCommand toCommand() {
        return RegisterCompanyCommand.builder()
                .name(name)
                .address(address)
                .description(description)
                .imgUrl(imgUrl)
                .build();
    }
}