package com.stubee.rollingadapter.out.persistence.member.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.member.entity.MemberEntity;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.SocialDetails;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements GenericMapper<MemberEntity, Member> {

    @Override
    public MemberEntity toEntity(final Member domain) {
        return MemberEntity.builder()
                .nickName(domain.nickName())
                .socialId(domain.socialDetails().socialId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberRole())
                .loginType(domain.socialDetails().loginType())
                .build();
    }

    public MemberEntity toIdEntity(final Member domain) {
        return MemberEntity.builder()
                .id(domain.id())
                .nickName(domain.nickName())
                .socialId(domain.socialDetails().socialId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberRole())
                .loginType(domain.socialDetails().loginType())
                .createdAt(domain.createdAt())
                .build();
    }

    @Override
    public Member toDomain(final MemberEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .socialDetails(socialDetails(entity))
                .nickName(entity.getNickName())
                .memberRole(entity.getMemberRole())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

    private SocialDetails socialDetails(final MemberEntity entity) {
        return SocialDetails.builder()
                .socialId(entity.getSocialId())
                .loginType(entity.getLoginType())
                .name(entity.getName())
                .email(entity.getEmail())
                .imageUrl(entity.getImageUrl())
                .build();
    }

}