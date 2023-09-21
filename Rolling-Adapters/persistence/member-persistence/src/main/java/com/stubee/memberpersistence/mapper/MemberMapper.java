package com.stubee.memberpersistence.mapper;

import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;

@DomainObjectMapper
public class MemberMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<MemberEntity, Member> {

    /** Member Entity Except Id */
    @Override
    public MemberEntity toEntity(final Member domain) {
        return MemberEntity.builder()
                .nickName(domain.memberDetails().nickName())
                .socialId(domain.socialDetails().socialId())
                .socialLoginId(domain.socialDetails().socialLoginId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberDetails().memberRole())
                .loginType(domain.socialDetails().loginType())
                .build();
    }

    /** Member Entity With Id */
    public MemberEntity toEntityWithId(final Member domain) {
        return MemberEntity.builder()
                .id(domain.memberId().getId())
                .nickName(domain.memberDetails().nickName())
                .socialId(domain.socialDetails().socialId())
                .socialLoginId(domain.socialDetails().socialLoginId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberDetails().memberRole())
                .loginType(domain.socialDetails().loginType())
                .createdAt(domain.memberDetails().createdAt())
                .modifiedAt(domain.memberDetails().modifiedAt())
                .build();
    }

    @Override
    public Member toDomain(final MemberEntity entity) {
        if(entity==null) {
            return null;
        }

        return Member.create(MemberId.of(entity.getId()), socialDetails(entity), memberDetails(entity));
    }

    private SocialDetails socialDetails(final MemberEntity entity) {
        return SocialDetails.create(entity.getSocialId(), entity.getSocialLoginId(), entity.getLoginType(),
                entity.getName(), entity.getEmail(), entity.getImageUrl());
    }

    private MemberDetails memberDetails(final MemberEntity entity) {
        return MemberDetails.create(entity.getNickName(), entity.getMemberRole(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}