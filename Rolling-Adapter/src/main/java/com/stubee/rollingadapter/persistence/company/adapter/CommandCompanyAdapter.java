package com.stubee.rollingadapter.persistence.company.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingadapter.persistence.company.entity.CompanyEntity;
import com.stubee.rollingadapter.persistence.company.mapper.CompanyMapper;
import com.stubee.rollingadapter.persistence.company.repository.CommandCompanyJpaRepository;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandCompanyAdapter implements CommandCompanyPort {

    private final CommandCompanyJpaRepository commandCompanyJpaRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company create(Company company) {
        return companyMapper.toDomain(save(companyMapper.toEntity(company)));
    }

    @Override
    public void update(Company company) {
        save(companyMapper.toEntityWithId(company));
    }

    @Override
    public void deleteById(CompanyId companyId) {
        commandCompanyJpaRepository.deleteById(companyId.id());
    }

    private CompanyEntity save(final CompanyEntity entity) {
        return commandCompanyJpaRepository.save(entity);
    }

}