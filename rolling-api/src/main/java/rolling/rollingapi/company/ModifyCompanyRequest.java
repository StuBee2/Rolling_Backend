package rolling.rollingapi.company;

import jakarta.validation.constraints.NotBlank;
import rolling.application.company.interactor.command.ModifyCompanyDetailsCommand;

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
    public ModifyCompanyDetailsCommand toCommand(final Long id) {
        return new ModifyCompanyDetailsCommand(id, name, description, address, addressEtc, imgUrl, rgb);
    }
}