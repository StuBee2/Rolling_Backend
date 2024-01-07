package rolling.rollingapi.company;

import jakarta.validation.constraints.NotBlank;
import rolling.application.company.interactor.command.RegisterCompanyCommand;

record RegisterCompanyRequest(
        @NotBlank String name,
        @NotBlank String address,
        String addressEtc,
        @NotBlank String description,
        String imgUrl,
        Integer rgb) {
    public RegisterCompanyCommand toCommand() {
        return RegisterCompanyCommand.create(name, address, addressEtc, description, imgUrl, rgb);
    }
}