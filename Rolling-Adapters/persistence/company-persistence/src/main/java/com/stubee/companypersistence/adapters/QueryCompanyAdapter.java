package com.stubee.companypersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.companyapplication.outports.query.CheckCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyPort;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.companyapplication.usecases.query.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
class QueryCompanyAdapter implements QueryCompanyPort, CheckCompanyPort {

    private final QueryCompanyRepository queryCompanyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public boolean check(final Long companyId) {
        return queryCompanyRepository.existsByCompanyId(companyId);
    }

    @Override
    public Optional<Company> findById(final Long id) {
        return Optional.ofNullable(companyMapper.toDomain(queryCompanyRepository.findById(id)));
    }

    @Override
    public Optional<CompanyQueryResponse> findInfoById(Long companyId) {
        return Optional.ofNullable(queryCompanyRepository.findInfoById(companyId));
    }

    @Override
    public List<Company> findByNameContaining(String companyName, PageRequest pageRequest) {
        return companyMapper.toDomainList(queryCompanyRepository.findByNameContaining(companyName, pageRequest));
    }

    @Override
    public List<Company> findByRegistrantId(Long registrantId, PageRequest pageRequest) {
        return companyMapper.toDomainList(queryCompanyRepository.findByRegistrantId(registrantId, pageRequest));
    }

    @Override
    public List<Company> findAll(PageRequest pageRequest) {
        return companyMapper.toDomainList(queryCompanyRepository.findAll(pageRequest));
    }

    @Override
    public List<Company> getOrderBy(final String gradeType) {
        return companyMapper.toDomainList(queryCompanyRepository.findOrderBy(gradeType));
    }

    @Override
    public boolean check(final String companyName) {
        return queryCompanyRepository.existsByCompanyName(companyName);
    }

}