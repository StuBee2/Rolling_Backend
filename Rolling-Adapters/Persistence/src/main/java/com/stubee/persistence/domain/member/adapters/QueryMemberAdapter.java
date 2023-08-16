package com.stubee.persistence.domain.member.adapters;

import com.stubee.persistence.common.annotations.Adapter;
import com.stubee.persistence.domain.member.mapper.MemberMapper;
import com.stubee.persistence.domain.member.repository.MemberJpaRepository;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingports.domain.member.ports.QueryMemberPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Adapter
@RequiredArgsConstructor
public class QueryMemberAdapter implements QueryMemberPort {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    public Optional<Member> findById(final UUID memberId) {
        return Optional.ofNullable(memberMapper.toDomain(
                memberJpaRepository.findById(memberId).orElse(null)));
    }

}