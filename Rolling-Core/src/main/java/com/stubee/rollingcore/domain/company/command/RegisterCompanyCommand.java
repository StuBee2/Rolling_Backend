package com.stubee.rollingcore.domain.company.command;

public record RegisterCompanyCommand(
        String name,
        String address,
        String description,
        String imgUrl) {
    public static RegisterCompanyCommand create(final String name, final String address,
                                                final String description, final String imgUrl) {
        return new RegisterCompanyCommand(name, address, description, imgUrl);
    }
}