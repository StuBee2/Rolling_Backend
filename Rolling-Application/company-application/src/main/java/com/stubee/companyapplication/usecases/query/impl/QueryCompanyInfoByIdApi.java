package com.stubee.companyapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.companyapplication.outports.query.QueryCompanyByIdPort;
import com.stubee.companyapplication.usecases.query.QueryCompanyInfoByIdUseCase;
import com.stubee.companyapplication.usecases.query.CompanyQueryResponse;
import com.stubee.rollingdomains.domain.company.events.CompanyViewedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyInfoByIdApi implements QueryCompanyInfoByIdUseCase {

    private final QueryCompanyByIdPort queryCompanyByIdPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public CompanyQueryResponse get(final Long companyId) {
        final CompanyQueryResponse response = queryCompanyByIdPort.getInfoById(companyId);

        applicationEventPublisher.publishEvent(CompanyViewedEvent.of(companyId));

        return response;
    }

}