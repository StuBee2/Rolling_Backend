package com.stubee.companyapplication.usecases.command.impl;

import com.stubee.companyapplication.usecases.command.ModifyCompanyDetailsCommand;
import com.stubee.companyapplication.usecases.command.RegisterCompanyCommand;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyMapperTest {

    @Test
    @DisplayName(value = "RegisterCompanyCommand to Company 변환 성공")
    void convertToDomain() {
        //given
        RegisterCompanyCommand command = RegisterCompanyCommand.create("나뭇잎마을", "나뭇잎마을", null, "히요옷", null, null);
        MemberId memberId = MemberId.of(1L);

        //when
        Company company = CompanyMapper.toDomain(command, memberId);

        //then
        assertEquals(command.name(), company.companyDetails().name());
        assertEquals(command.address(), company.companyDetails().companyAddress().address());
        assertEquals(command.description(), company.companyDetails().description());
        assertEquals(command.imgUrl(), company.companyDetails().companyLogo().url());
        assertEquals(command.rgb(), company.companyDetails().companyLogo().rgb());
        assertEquals(memberId, company.companyDetails().registrantId());
    }

    @Test
    @DisplayName(value = "ModifyCompanyDetailsCommand to Details 변환 성공")
    void convertToDetails() {
        //given
        ModifyCompanyDetailsCommand command = new ModifyCompanyDetailsCommand(1L, "나뭇잎마을2", "사마귀마을2", "스스슥", null, null, null);
        MemberId memberId = MemberId.of(1L);

        //when
        CompanyDetails companyDetails = CompanyMapper.toDetails(command, memberId);

        //then
        assertEquals(command.name(), companyDetails.name());
        assertEquals(command.description(), companyDetails.description());
        assertEquals(command.companyAddress(), companyDetails.companyAddress().address());
        assertEquals(command.companyAddressEtc(), companyDetails.companyAddress().etc());
        assertEquals(command.url(), companyDetails.companyLogo().url());
        assertEquals(command.rgb(), companyDetails.companyLogo().rgb());
    }

}