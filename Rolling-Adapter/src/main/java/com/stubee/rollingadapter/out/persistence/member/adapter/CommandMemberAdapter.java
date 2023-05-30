package com.stubee.rollingadapter.out.persistence.member.adapter;

import com.stubee.rollingadapter.out.persistence.member.entity.MemberEntity;
import com.stubee.rollingadapter.out.persistence.member.mapper.MemberMapper;
import com.stubee.rollingadapter.out.persistence.member.repository.CommandMemberJpaRepository;
import com.stubee.rollingapplication.domain.member.port.spi.CommandMemberPort;
import com.stubee.rollingcore.domain.member.dto.response.MemberProfile;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandMemberAdapter implements CommandMemberPort {

    private final CommandMemberJpaRepository commandMemberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member save(final Member member) {
        return memberMapper.toDomain(commandMemberJpaRepository.save(memberMapper.toIdEntity(member)));
    }

    private Member save(final MemberEntity memberEntity) {
        return memberMapper.toDomain(commandMemberJpaRepository.save(memberEntity));
    }

    @Override
    public Member saveOrUpdate(final MemberProfile memberProfile) {
        MemberEntity memberEntity = commandMemberJpaRepository.findBySocialIdAndLoginType(memberProfile.socialId(), memberProfile.loginType())
                .orElse(null);

        if(memberEntity == null) {
            return save(memberProfile.toMember());
        } else {
            return save(memberEntity.update(memberProfile.name(), memberProfile.email()));
        }
    }

}