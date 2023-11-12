package com.stubee.memberpersistence;

import com.stubee.memberpersistence.adapters.CommandMemberAdapter;
import com.stubee.memberpersistence.mapper.MemberMapper;
import com.stubee.memberpersistence.repository.MemberJpaRepository;
import com.stubee.persistencecommons.PersistenceAdapterTest;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
@ContextConfiguration(classes = {CommandMemberAdapter.class, MemberMapper.class, MemberJpaRepository.class})
public class CommandMemberAdapterTest {

    @Autowired
    private CommandMemberAdapter commandMemberAdapter;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("Member 생성 성공")
    void MEMBER_생성_성공() {
        //given
        Member member = MemberTestUtils.createDomain(
                "111111",
                "suw0n",
                LoginType.GITHUB,
                "최수원",
                "rolling@gmail.com",
                null);

        //when
        MemberId id = commandMemberAdapter.saveExceptId(member).memberId();

        //then
        assertTrue(memberJpaRepository.existsById(id.getId()));
    }

}