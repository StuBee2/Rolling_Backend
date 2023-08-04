package com.stubee.rollingadapter.persistence.employment.adapter;

import com.stubee.rollingadapter.persistence.employment.mapper.EmploymentMapper;
import com.stubee.rollingadapter.persistence.employment.repository.EmploymentJpaRepository;
import com.stubee.rollingapplication.domain.employment.port.spi.CommandEmploymentPort;
import com.stubee.rollingcore.domain.employment.model.Employment;
import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandEmploymentAdapter implements CommandEmploymentPort {

    private final EmploymentJpaRepository employmentJpaRepository;
    private final EmploymentMapper employmentMapper;

    @Override
    public Employment register(final Employment employment) {
        return employmentMapper.toDomain(employmentJpaRepository.save(employmentMapper.toEntity(employment)));
    }

}