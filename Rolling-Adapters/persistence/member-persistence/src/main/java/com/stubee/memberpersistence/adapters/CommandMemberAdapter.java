package com.stubee.memberpersistence.adapters;

import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.persistencecommons.commons.entity.MemberEntity;
import com.stubee.memberpersistence.mapper.MemberMapper;
import com.stubee.memberpersistence.repository.MemberJpaRepository;
import com.stubee.persistencecommons.commons.annotations.Adapter;
import com.stubee.rollingdomains.domain.email.model.SendWelcomeEmailEvent;
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
        final Member member = memberJpaRepository.findBySocialIdAndLoginType(memberProfile.socialId(), memberProfile.loginType())
                .map(memberMapper::toDomain)
                .orElse(null);

        if(member == null) {
            publishSendWelcomeEmailEvent(memberProfile.email());

            return saveExceptId(memberProfile.toMember());
        } else {
            return saveWithId(member.updateLoginId(memberProfile.socialLoginId()));
        }
    }

    private Member save(final MemberEntity memberEntity) {
        return memberMapper.toDomain(memberJpaRepository.save(memberEntity));
    }

    private void publishSendWelcomeEmailEvent(final String memberEmail) {
        if(memberEmail!=null) {
            applicationEventPublisher.publishEvent(SendWelcomeEmailEvent.create(memberEmail));
        }
    }

}