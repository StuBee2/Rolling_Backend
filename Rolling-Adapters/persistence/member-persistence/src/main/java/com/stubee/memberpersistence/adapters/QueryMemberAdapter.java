package com.stubee.memberpersistence.adapters;

import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.memberpersistence.mapper.MemberMapper;
import com.stubee.memberpersistence.repository.MemberJpaRepository;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.rollingdomains.domain.member.model.Member;
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

    @Override
    public boolean check(final String nickname) {
        return memberJpaRepository.existsByNickName(nickname);
    }

}