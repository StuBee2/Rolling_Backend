package com.stubee.companyapplication.usecases.command;

import com.stubee.companyapplication.commands.ChangeCompanyStatusCommand;

public interface ChangeCompanyStatusUseCase {

    void change(ChangeCompanyStatusCommand command);

}