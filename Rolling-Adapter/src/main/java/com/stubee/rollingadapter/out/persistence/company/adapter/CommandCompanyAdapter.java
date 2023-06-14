package com.stubee.rollingadapter.out.persistence.company.adapter;

import com.stubee.rollingadapter.out.persistence.company.mapper.CompanyMapper;
import com.stubee.rollingadapter.out.persistence.company.repository.CommandCompanyJpaRepository;
import com.stubee.rollingapplication.domain.company.port.spi.CommandCompanyPort;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandCompanyAdapter implements CommandCompanyPort {

    private final CommandCompanyJpaRepository commandCompanyJpaRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company save(Company company) {
        return companyMapper.toDomain(commandCompanyJpaRepository.save(companyMapper.toEntity(company)));
    }

    @Override
    public void update(Company company) {
        commandCompanyJpaRepository.save(companyMapper.toEntityWithId(company));
    }

    @Override
    public void deleteById(CompanyId companyId) {
        commandCompanyJpaRepository.deleteById(companyId.id());
    }

}