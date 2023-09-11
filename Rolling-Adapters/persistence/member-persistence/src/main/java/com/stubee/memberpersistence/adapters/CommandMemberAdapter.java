package com.stubee.memberpersistence.adapters;

import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.memberpersistence.mapper.MemberMapper;
import com.stubee.memberpersistence.repository.MemberJpaRepository;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.rollingdomains.domain.auth.exception.WrongLoginTypeException;
import com.stubee.rollingdomains.domain.member.events.MemberRegisteredEvent;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Adapter
@RequiredArgsConstructor
public class CommandMemberAdapter implements CommandMemberPort {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Member saveWithId(final Member member) {
        return save(memberMapper.toEntityWithId(member));
    }

    @Override
    public Member saveExceptId(final Member member) {
        return save(memberMapper.toEntity(member));
    }

    @Override
    public Member saveOrUpdate(final MemberProfile memberProfile) {
        final Member member = memberJpaRepository.findBySocialIdAndLoginTypeOrEmail(memberProfile.socialId(),
                        memberProfile.loginType(), memberProfile.email())
                .map(memberMapper::toDomain)
                .orElse(null);

        if(member == null) {
            publishMemberRegisteredEvent(memberProfile.email());

            return saveExceptId(memberProfile.toMember());
        } else if(member.socialDetails().loginType().equals(memberProfile.loginType())) {
            return saveWithId(member.updateLoginId(memberProfile.socialLoginId()));
        } else {
            throw WrongLoginTypeException.EXCEPTION;
        }
    }

    private Member save(final MemberEntity memberEntity) {
        return memberMapper.toDomain(memberJpaRepository.save(memberEntity));
    }

    private void publishMemberRegisteredEvent(final String memberEmail) {
        if(memberEmail!=null) {
            applicationEventPublisher.publishEvent(MemberRegisteredEvent.create(memberEmail));
        }
    }

}