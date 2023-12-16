package com.stubee.memberpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
class QueryMemberAdapter implements QueryMemberPort {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    public Optional<Member> findById(final Long memberId) {
        return Optional.ofNullable(memberMapper.toDomain(
                memberJpaRepository.findById(memberId).orElse(null)));
    }

    @Override
    public boolean check(final String nickname) {
        return memberJpaRepository.existsByNickName(nickname);
    }

}