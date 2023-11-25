package com.stubee.loggingpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.loggingpersistence.mapper.CompanyViewLoggingMapper;
import com.stubee.loggingpersistence.repository.CompanyViewLoggingRepository;
import com.stubee.rollingdomains.logging.model.CompanyViewLogging;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandCompanyViewLoggingAdapter implements CommandLoggingPort<CompanyViewLogging> {

    private final CompanyViewLoggingMapper companyViewLoggingMapper;
    private final CompanyViewLoggingRepository companyViewLoggingRepository;

    @Override
    public CompanyViewLogging save(CompanyViewLogging logging) {
        return companyViewLoggingMapper.toDomain(companyViewLoggingRepository.save(companyViewLoggingMapper.toEntity(logging)));
    }

}