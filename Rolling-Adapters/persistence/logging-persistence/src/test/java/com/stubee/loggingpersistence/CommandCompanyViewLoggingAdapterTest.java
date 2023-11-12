package com.stubee.loggingpersistence;

import com.stubee.loggingpersistence.adapters.CommandCompanyViewLoggingAdapter;
import com.stubee.loggingpersistence.mapper.CompanyViewLoggingMapper;
import com.stubee.loggingpersistence.repository.CompanyViewLoggingRepository;
import com.stubee.persistencecommons.PersistenceAdapterTest;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.logging.model.CompanyViewLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@PersistenceAdapterTest
@ContextConfiguration(classes = {CommandCompanyViewLoggingAdapter.class, CompanyViewLoggingMapper.class, CompanyViewLoggingRepository.class})
public class CommandCompanyViewLoggingAdapterTest {

    @Autowired
    private CommandCompanyViewLoggingAdapter commandCompanyViewLoggingAdapter;

    @Autowired
    private CompanyViewLoggingRepository companyViewLoggingRepository;

    @Test
    @DisplayName(value = "기업조회 로깅 생성 성공")
    void COMPANY_VIEW_LOGGING_생성_성공() {
        //given
        CompanyViewLogging companyViewLogging = CompanyViewLoggingUtils.create(
                MemberId.of(-1L),
                CompanyId.of(1L));

        //when
        Long id = commandCompanyViewLoggingAdapter.save(companyViewLogging).id();

        //then
        assertTrue(companyViewLoggingRepository.existsById(id));
    }

}