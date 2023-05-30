package com.stubee.rollingadapter.out.persistence.company.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.company.entity.CompanyEntity;
import com.stubee.rollingcore.domain.company.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements GenericMapper<CompanyEntity, Company> {

    @Override
    public CompanyEntity toEntity(final Company domain) {
        return CompanyEntity.builder()
                .name(domain.name())
                .address(domain.address())
                .description(domain.description())
                .imgUrl(domain.imgUrl())
                .totalGrade(domain.totalGrade())
                .balanceGrade(domain.balanceGrade())
                .salaryGrade(domain.salaryGrade())
                .welfareGrade(domain.welfareGrade())
                .registrantId(domain.registrantId())
                .build();
    }

    @Override
    public Company toDomain(final CompanyEntity entity) {
        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .description(entity.getDescription())
                .imgUrl(entity.getImgUrl())
                .totalGrade(entity.getTotalGrade())
                .balanceGrade(entity.getBalanceGrade())
                .salaryGrade(entity.getSalaryGrade())
                .welfareGrade(entity.getWelfareGrade())
                .registrantId(entity.getRegistrantId())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}