package com.stubee.employmentpersistence.adapters;

import com.stubee.employmentapplication.outports.RegisterEmploymentPort;
import com.stubee.employmentpersistence.mapper.EmploymentMapper;
import com.stubee.employmentpersistence.repository.CommandEmploymentJpaRepository;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandEmploymentAdapter implements RegisterEmploymentPort {

    private final CommandEmploymentJpaRepository commandEmploymentJpaRepository;
    private final EmploymentMapper employmentMapper;

    @Override
    public Employment register(final Employment employment) {
        return employmentMapper.toDomain(commandEmploymentJpaRepository.save(employmentMapper.toEntity(employment)));
    }

}