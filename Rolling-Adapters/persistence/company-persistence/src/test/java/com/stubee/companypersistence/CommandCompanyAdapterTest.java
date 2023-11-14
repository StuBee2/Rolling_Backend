package com.stubee.companypersistence;

import com.stubee.companypersistence.adapters.CommandCompanyAdapter;
import com.stubee.companypersistence.mapper.CompanyMapper;
import com.stubee.companypersistence.repository.CommandCompanyJpaRepository;
import com.stubee.persistencecommons.PersistenceAdapterTest;
import com.stubee.rollingdomains.domain.company.model.Address;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyGrades;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
@ContextConfiguration(classes = {CommandCompanyAdapter.class, CompanyMapper.class, CommandCompanyJpaRepository.class})
public class CommandCompanyAdapterTest {

    @Autowired
    private CommandCompanyAdapter commandCompanyAdapter;

    @Autowired
    private CommandCompanyJpaRepository companyJpaRepository;

    @Test
    @DisplayName(value = "Company 생성 성공")
    void COMPANY_생성_성공() {
        //given
        String name = "木ノ葉隠れの里";
        String description = "日本で最高の会社";
        Address address = Address.of("木ノ葉隠れの里", null);
        Company company = CompanyTestUtils.create(name, description, address, CompanyGrades.zero());

        //when
        CompanyId id = commandCompanyAdapter.register(company).companyId();

        //then
        assertTrue(companyJpaRepository.existsById(id.getId()));
    }
}