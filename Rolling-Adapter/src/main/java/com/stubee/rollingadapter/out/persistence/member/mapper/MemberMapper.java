package com.stubee.rollingadapter.out.persistence.member.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.member.entity.MemberEntity;
import com.stubee.rollingcore.domain.member.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements GenericMapper<MemberEntity, Member> {

    @Override
    public MemberEntity toEntity(final Member domain) {
        return MemberEntity.builder()
                .nickName(domain.nickName())
                .socialId(domain.socialId())
                .name(domain.name())
                .email(domain.email())
                .imageUrl(domain.imageUrl())
                .memberRole(domain.memberRole())
                .loginType(domain.loginType())
                .build();
    }

    public MemberEntity toIdEntity(final Member domain) {
        return MemberEntity.builder()
                .id(domain.id())
                .nickName(domain.nickName())
                .socialId(domain.socialId())
                .name(domain.name())
                .email(domain.email())
                .imageUrl(domain.imageUrl())
                .memberRole(domain.memberRole())
                .loginType(domain.loginType())
                .createdAt(domain.createdAt())
                .build();
    }

    @Override
    public Member toDomain(final MemberEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .nickName(entity.getNickName())
                .socialId(entity.getSocialId())
                .name(entity.getName())
                .email(entity.getEmail())
                .imageUrl(entity.getImageUrl())
                .memberRole(entity.getMemberRole())
                .loginType(entity.getLoginType())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}