package com.stubee.companypersistence.adapters;

import com.stubee.companyapplication.outports.CommandCompanyPort;
import com.stubee.companypersistence.mapper.CompanyMapper;
import com.stubee.companypersistence.repository.CommandCompanyJpaRepository;
import com.stubee.persistencecommons.commons.annotations.Adapter;
import com.stubee.persistencecommons.commons.entity.CompanyEntity;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandCompanyAdapter implements CommandCompanyPort {

    private final CommandCompanyJpaRepository commandCompanyJpaRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company create(final Company company) {
        return companyMapper.toDomain(save(companyMapper.toEntity(company)));
    }

    @Override
    public void update(final Company company) {
        save(companyMapper.toEntityWithId(company));
    }

    @Override
    public void deleteById(final CompanyId companyId) {
        commandCompanyJpaRepository.deleteById(companyId.getId());
    }

    private CompanyEntity save(final CompanyEntity entity) {
        return commandCompanyJpaRepository.save(entity);
    }

}