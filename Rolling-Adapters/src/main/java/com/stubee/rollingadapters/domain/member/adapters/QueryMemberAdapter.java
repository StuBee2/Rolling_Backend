package com.stubee.rollingadapters.domain.member.adapters;

import com.stubee.rollingexternal.persistence.domain.member.mapper.MemberMapper;
import com.stubee.rollingcommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingexternal.persistence.domain.member.repository.MemberJpaRepository;
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