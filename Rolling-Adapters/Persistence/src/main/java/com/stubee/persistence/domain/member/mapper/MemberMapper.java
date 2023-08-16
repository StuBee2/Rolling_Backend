package com.stubee.persistence.domain.member.mapper;

import com.stubee.persistence.common.mapper.DomainEntityMapper;
import com.stubee.persistence.common.annotations.Mapper;
import com.stubee.persistence.domain.member.entity.MemberEntity;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;

@Mapper
public class MemberMapper implements DomainEntityMapper<MemberEntity, Member> {

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

        return Member.create(MemberId.create(entity.getId()), socialDetails(entity), memberDetails(entity));
    }

    private SocialDetails socialDetails(final MemberEntity entity) {
        return SocialDetails.create(entity.getSocialId(), entity.getSocialLoginId(), entity.getLoginType(),
                entity.getName(), entity.getEmail(), entity.getImageUrl());
    }

    private MemberDetails memberDetails(final MemberEntity entity) {
        return MemberDetails.create(entity.getNickName(), entity.getMemberRole(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}