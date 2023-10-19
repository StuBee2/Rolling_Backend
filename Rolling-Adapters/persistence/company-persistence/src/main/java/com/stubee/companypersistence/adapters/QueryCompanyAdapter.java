package com.stubee.companypersistence.adapters;

import com.stubee.companyapplication.outports.query.CheckCompanyPort;
import com.stubee.companyapplication.outports.query.QueryCompanyPort;
import com.stubee.companypersistence.mapper.CompanyMapper;
import com.stubee.companypersistence.repository.QueryCompanyRepository;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.companyapplication.usecases.query.response.CompanyQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class QueryCompanyAdapter implements QueryCompanyPort, CheckCompanyPort {

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
        return companyMapper.toDomainList(queryCompanyRepository.findByNameContaining(companyName, pageRequest));
    }

    @Override
    public List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest) {
        return companyMapper.toDomainList(queryCompanyRepository.findByRegistrantId(registrantId, pageRequest));
    }

    @Override
    public List<Company> findAll(PageRequest pageRequest) {
        return companyMapper.toDomainList(queryCompanyRepository.findAll(pageRequest));
    }

    @Override
    public List<Company> getByTotalGrade() {
        return companyMapper.toDomainList(queryCompanyRepository.findByTotalGrade());
    }

    @Override
    public List<Company> getBySalaryAndBenefits() {
        return companyMapper.toDomainList(queryCompanyRepository.findBySalaryAndBenefits());
    }

    @Override
    public List<Company> getByWorkLifeBalance() {
        return companyMapper.toDomainList(queryCompanyRepository.findByWorkLifeBalance());
    }

    @Override
    public List<Company> getByOrganizationalCulture() {
        return companyMapper.toDomainList(queryCompanyRepository.findByOrganizationalCulture());
    }

    @Override
    public List<Company> getByCareerAdvancement() {
        return companyMapper.toDomainList(queryCompanyRepository.findByCareerAdvancement());
    }

    @Override
    public boolean check(final String companyName) {
        return queryCompanyRepository.existsByCompanyName(companyName);
    }

}