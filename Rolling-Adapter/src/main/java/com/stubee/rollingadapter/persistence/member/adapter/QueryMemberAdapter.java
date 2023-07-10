package com.stubee.rollingadapter.persistence.member.adapter;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingapplication.common.annotation.Adapter;
import com.stubee.rollingadapter.persistence.member.mapper.MemberMapper;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import static com.stubee.rollingadapter.persistence.member.entity.QMemberEntity.memberEntity;

@Adapter
@RequiredArgsConstructor
public class QueryMemberAdapter implements QueryMemberPort {

    private final JPAQueryFactory jpaQueryFactory;
    private final MemberMapper memberMapper;

    @Override
    public Optional<Member> findById(UUID memberId) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.id.eq(memberId))
                .fetchOne())
                .map(memberMapper::toDomain);
    }

}