package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.companyapplication.outports.command.DeleteCompanyPort;
import com.stubee.companyapplication.outports.command.RegisterCompanyPort;
import com.stubee.companyapplication.outports.command.UpdateCompanyPort;
import com.stubee.companyapplication.outports.query.CheckCompanyExistencePort;
import com.stubee.companyapplication.outports.query.CheckCompanyNameDuplicationPort;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.services.*;
import com.stubee.rollingdomains.domain.company.services.commands.ChangeCompanyStatusCommand;
import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class CompanyDomainService implements RegisterCompanyService, ChangeCompanyStatusService, DeleteCompanyService,
        UpdateCompanyListService, CheckCompanyExistenceService {

    private final RegisterCompanyPort registerCompanyPort;
    private final UpdateCompanyPort updateCompanyPort;
    private final DeleteCompanyPort deleteCompanyPort;
    private final CheckCompanyNameDuplicationPort checkCompanyNameDuplicationPort;
    private final CheckCompanyExistencePort checkCompanyExistencePort;
    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public Company register(final RegisterCompanyCommand command, final MemberId memberId) {
        checkCompanyNameDuplicationPort.checkByName(command.name());

        return registerCompanyPort.register(command.toDomain(memberId));
    }

    @Override
    public void change(final ChangeCompanyStatusCommand command) {
        final Company company = queryCompanyByIdPort.getById(command.companyId())
                .updateStatus(command.status());

        updateCompanyPort.update(company);
    }

    @Override
    public void delete(final DeleteCompanyCommand command) {
        final Company company = queryCompanyByIdPort.getById(command.companyId().getId());

        deleteCompanyPort.deleteById(company.companyId());
    }

    @Override
    public void checkById(final UUID id) {
        checkCompanyExistencePort.checkById(id);
    }

    @Override
    public void updateAll(final List<Company> companyList) {
        updateCompanyPort.updateAll(companyList);
    }

}