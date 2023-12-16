package com.stubee.companypersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.companyapplication.outports.command.CommandCompanyPort;
import com.stubee.persistencecommons.entity.CompanyEntity;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
class CommandCompanyAdapter implements CommandCompanyPort {

    private final CommandCompanyJpaRepository commandCompanyJpaRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company register(final Company company) {
        return companyMapper.toDomain(save(companyMapper.toEntity(company)));
    }

    @Override
    public void update(final Company company) {
        save(companyMapper.toEntityWithId(company));
    }

    @Override
    public void updateAll(final List<Company> companyList) {
        companyList.forEach(this::update);
    }

    @Override
    public void deleteById(final CompanyId companyId) {
        commandCompanyJpaRepository.deleteById(companyId.getId());
    }

    private CompanyEntity save(final CompanyEntity entity) {
        return commandCompanyJpaRepository.save(entity);
    }

}