package com.stubee.companyapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.companyapplication.outports.command.CommandCompanyPort;
import com.stubee.companyapplication.outports.query.CheckCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.exception.DuplicatedCompanyNameException;
import com.stubee.rollingdomains.domain.company.model.*;
import com.stubee.rollingdomains.domain.company.services.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@DomainService
@RequiredArgsConstructor
class CompanyDomainService implements
        RegisterCompanyService,
        ModifyCompanyService,
        DeleteCompanyService,
        UpdateCompanyListService,
        CheckCompanyExistenceService {

    private final CommandCompanyPort commandCompanyPort;
    private final CheckCompanyPort checkCompanyPort;
    private final QueryCompanyByIdPort queryCompanyByIdPort;

    @Override
    public Company register(final Company company) {
        this.checkByName(company.companyDetails().name());

        return commandCompanyPort.register(company);
    }

    private void checkByName(final String name) {
        if(checkCompanyPort.check(name)) {
            throw DuplicatedCompanyNameException.EXCEPTION;
        }
    }

    @Override
    public void delete(final CompanyId companyId) {
        this.checkById(companyId.getId());

        commandCompanyPort.deleteById(companyId);
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
    public void modify(final Long id, final CompanyDetails companyDetails) {
        final Company company = this.getById(id);

        company.isAuthor(companyDetails.registrantId());

        if(!Objects.equals(company.companyDetails().name(), companyDetails.name())) {
            checkByName(companyDetails.name());
        }

        commandCompanyPort.update(company.update(companyDetails));
    }

    @Override
    public void modify(final CompanyId companyId, final CompanyStatus status) {
        final Company company = this.getById(companyId.getId());

        commandCompanyPort.update(company.update(status));
    }

    private Company getById(final Long id) {
        return queryCompanyByIdPort.findById(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

}