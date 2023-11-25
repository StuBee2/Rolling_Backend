package com.stubee.loggingpersistence;

import com.stubee.loggingpersistence.adapters.CommandHistoryLoggingAdapter;
import com.stubee.loggingpersistence.mapper.HistoryLoggingMapper;
import com.stubee.loggingpersistence.repository.HistoryLoggingJpaRepository;
import com.stubee.persistencecommons.PersistenceAdapterTest;
import com.stubee.rollingdomains.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@PersistenceAdapterTest
@ContextConfiguration(classes = {CommandHistoryLoggingAdapter.class, HistoryLoggingMapper.class, HistoryLoggingJpaRepository.class})
public class CommandHistoryLoggingAdapterTest {

    @Autowired
    private CommandHistoryLoggingAdapter commandHistoryLoggingAdapter;

    @Autowired
    private HistoryLoggingJpaRepository historyLoggingJpaRepository;

    @Test
    @DisplayName(value = "히스토리 로깅 생성 성공")
    void HISTORY_LOGGING_생성_성공() {
        //given
        HistoryLogging historyLogging = HistoryLoggingUtils.create(
                "연봉순위 조회",
                "WEB",
                MemberId.of(-1L));

        //when
        Long id = commandHistoryLoggingAdapter.save(historyLogging).id();

        //then
        assertTrue(historyLoggingJpaRepository.existsById(id));
    }

}