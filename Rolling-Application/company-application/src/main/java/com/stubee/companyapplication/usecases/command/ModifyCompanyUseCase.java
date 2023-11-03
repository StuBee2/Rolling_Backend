package com.stubee.companyapplication.usecases.command;

public interface ModifyCompanyUseCase {

    void modify(ModifyCompanyDetailsCommand command);

    void modify(ModifyCompanyStatusCommand command);

}