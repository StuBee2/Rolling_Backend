package com.stubee.companyapplication.usecases.command;

public record RegisterCompanyCommand(
        String name,
        String address,
        String addressEtc,
        String description,
        String imgUrl,
        Integer rgb) {
    public static RegisterCompanyCommand create(final String name, final String address, final String addressEtc,
                                                final String description, final String imgUrl, final Integer rgb) {
        return new RegisterCompanyCommand(name, address, addressEtc, description, imgUrl, rgb);
    }
}