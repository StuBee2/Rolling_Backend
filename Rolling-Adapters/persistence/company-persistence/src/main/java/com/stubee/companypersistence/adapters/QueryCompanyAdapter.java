package com.stubee.companypersistence.adapters;

import com.stubee.applicationcommons.ports.company.CheckCompanyExistencePort;
import com.stubee.companyapplication.outports.QueryCompanyPort;
import com.stubee.companypersistence.mapper.CompanyMapper;
import com.stubee.companypersistence.repository.QueryCompanyRepository;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.companyapplication.services.query.response.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class QueryCompanyAdapter implements QueryCompanyPort, CheckCompanyExistencePort {

    private final QueryCompanyRepository queryCompanyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public boolean check(final UUID companyId) {
        return queryCompanyRepository.existsByCompanyId(companyId);
    }

    @Override
    public Optional<Company> findById(final UUID id) {
        return Optional.ofNullable(companyMapper.toDomain(queryCompanyRepository.findById(id)));
    }

    @Override
    public Optional<CompanyQueryResponse> findInfoById(UUID companyId) {
        return Optional.ofNullable(queryCompanyRepository.findInfoById(companyId));
    }

    @Override
    public List<Company> findByNameContaining(String companyName, PageRequest pageRequest) {
        return queryCompanyRepository.findByNameContaining(companyName, pageRequest)
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest) {
        return queryCompanyRepository.findByRegistrantId(registrantId, pageRequest)
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findAll(PageRequest pageRequest) {
        return queryCompanyRepository.findAll(pageRequest)
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByTotalGrade() {
        return queryCompanyRepository.findByTotalGrade()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findBySalaryAndBenefits() {
        return queryCompanyRepository.findBySalaryAndBenefits()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByWorkLifeBalance() {
        return queryCompanyRepository.findByWorkLifeBalance()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByOrganizationalCulture() {
        return queryCompanyRepository.findByOrganizationalCulture()
                .stream().map(companyMapper::toDomain).toList();
    }

    @Override
    public List<Company> findByCareerAdvancement() {
        return queryCompanyRepository.findByCareerAdvancement()
                .stream().map(companyMapper::toDomain).toList();
    }

}