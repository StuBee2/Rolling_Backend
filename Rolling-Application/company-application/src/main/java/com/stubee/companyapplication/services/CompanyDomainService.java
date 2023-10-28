package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.companyapplication.outports.command.CommandCompanyPort;
import com.stubee.companyapplication.outports.query.CheckCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.exception.DuplicatedCompanyNameException;
import com.stubee.rollingdomains.domain.company.model.Address;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyLogo;
import com.stubee.rollingdomains.domain.company.services.*;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyStatusCommand;
import com.stubee.rollingdomains.domain.company.services.commands.DeleteCompanyCommand;
import com.stubee.rollingdomains.domain.company.services.commands.ModifyCompanyDetailsCommand;
import com.stubee.rollingdomains.domain.company.services.commands.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@DomainService
@RequiredArgsConstructor
public class CompanyDomainService implements RegisterCompanyService, ModifyCompanyService, DeleteCompanyService,
        UpdateCompanyListService, CheckCompanyExistenceService {

    private final CommandCompanyPort commandCompanyPort;
    private final CheckCompanyPort checkCompanyPort;
    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public Company register(final RegisterCompanyCommand command, final MemberId memberId) {
        this.checkByName(command.name());

        return commandCompanyPort.register(command.toDomain(memberId));
    }

    private void checkByName(final String name) {
        if(checkCompanyPort.check(name)) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }
    }

    @Override
    public void delete(final DeleteCompanyCommand command) {
        final Company company = this.getById(command.companyId().getId());

        commandCompanyPort.deleteById(company.companyId());
    }

    private Company getById(final Long id) {
        return queryCompanyByIdPort.findById(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    @Override
    public void checkById(final Long id) {
        if(checkCompanyPort.check(id)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

    @Override
    public void updateAll(final List<Company> companyList) {
        commandCompanyPort.updateAll(companyList);
    }

    @Override
    public void modify(final ModifyCompanyDetailsCommand command, final MemberId memberId) {
        final Company company = getById(command.id());

        company.isAuthor(memberId);

        if(!Objects.equals(company.companyDetails().name(), command.name())) {
            checkByName(command.name());
        }

        commandCompanyPort.update(company.update(command.name(), command.description(),
                Address.of(command.companyAddress(), command.companyAddressEtc()),
                CompanyLogo.of(command.url(), command.rgb())));
    }

    @Override
    public void modify(final ModifyCompanyStatusCommand command) {
        final Company company = this.getById(command.companyId());

        commandCompanyPort.update(company.update(command.status()));
    }

}