package com.stubee.memberpersistence;

import com.stubee.memberpersistence.mapper.MemberMapper;
import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.persistencecommons.mapper.DomainObjectMapper;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MemberMapperTest {

    private DomainObjectMapper<MemberEntity, Member> memberMapper;

    @BeforeEach
    void before() {
        memberMapper = new MemberMapper();
    }

    @Test
    @DisplayName(value = "Domain To Entity 변환 성공")
    void test() {
        Member domain = MemberTestUtils.createDomain(
                "111111",
                "suw0n",
                LoginType.GITHUB,
                "최수원",
                "rolling@gmail.com",
                null);

        MemberEntity entity = memberMapper.toEntity(domain);

        assertDoesNotThrow(() -> MemberTestUtils.isEqual(domain, entity));
    }

    @Test
    @DisplayName(value = "Entity To Domain 변환 성공")
    void test2() {
        MemberEntity entity = MemberTestUtils.createEntity(
                "111111",
                "suw0n",
                LoginType.GITHUB,
                "최수원",
                "rolling@gmail.com",
                null);

        Member domain = memberMapper.toDomain(entity);

        assertDoesNotThrow(() -> MemberTestUtils.isEqual(domain, entity));
    }

}