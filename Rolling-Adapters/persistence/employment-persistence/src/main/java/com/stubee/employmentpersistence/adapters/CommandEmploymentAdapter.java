package com.stubee.employmentpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.employmentapplication.outports.RegisterEmploymentPort;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class CommandEmploymentAdapter implements RegisterEmploymentPort {

    private final CommandEmploymentJpaRepository commandEmploymentJpaRepository;
    private final EmploymentMapper employmentMapper;

    @Override
    public Employment register(final Employment employment) {
        return employmentMapper.toDomain(commandEmploymentJpaRepository.save(employmentMapper.toEntity(employment)));
    }

}