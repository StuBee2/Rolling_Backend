package com.stubee.rollingadapter.persistence.member.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingadapter.persistence.member.entity.MemberEntity;
import com.stubee.rollingadapter.persistence.member.mapper.MemberMapper;
import com.stubee.rollingadapter.persistence.member.repository.CommandMemberJpaRepository;
import com.stubee.rollingapplication.domain.member.port.spi.CommandMemberPort;
import com.stubee.rollingcore.domain.member.model.MemberProfile;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CommandMemberAdapter implements CommandMemberPort {

    private final CommandMemberJpaRepository commandMemberJpaRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member saveWithId(Member member) {
        return save(memberMapper.toEntityWithId(member));
    }

    @Override
    public Member saveExceptId(Member member) {
        return save(memberMapper.toEntity(member));
    }

    @Override
    public Member saveOrUpdate(MemberProfile memberProfile) {
        Member member = commandMemberJpaRepository.findBySocialIdAndLoginType(memberProfile.socialId(), memberProfile.loginType())
                .map(memberMapper::toDomain)
                .orElse(null);

        if(member == null) {
            return saveExceptId(memberProfile.toMember());
        } else {
            return saveWithId(member.updateSocialDetails(memberProfile.name(), memberProfile.email()));
        }
    }

    private Member save(final MemberEntity memberEntity) {
        return memberMapper.toDomain(commandMemberJpaRepository.save(memberEntity));
    }

}