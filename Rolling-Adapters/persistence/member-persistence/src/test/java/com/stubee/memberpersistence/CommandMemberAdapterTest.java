package com.stubee.memberpersistence;

import com.stubee.memberpersistence.adapters.CommandMemberAdapter;
import com.stubee.memberpersistence.repository.MemberJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("com.stubee.persistencecommons")
@ContextConfiguration(classes = {CommandMemberAdapter.class, MemberJpaRepository.class})
public class CommandMemberAdapterTest {

    @Autowired
    private CommandMemberAdapter commandMemberAdapter;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("멤버 생성 성공 테스트")
    void CREATE_MEMBER_SUCCESS() {
        //given

        //when

        //then
    }

}