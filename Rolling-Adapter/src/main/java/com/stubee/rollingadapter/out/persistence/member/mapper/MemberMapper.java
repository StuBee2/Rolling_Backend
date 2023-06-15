package com.stubee.rollingadapter.out.persistence.member.mapper;

import com.stubee.rollingadapter.out.common.mapper.GenericMapper;
import com.stubee.rollingadapter.out.persistence.member.entity.MemberEntity;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.member.model.MemberDetails;
import com.stubee.rollingcore.domain.member.model.MemberId;
import com.stubee.rollingcore.domain.member.model.SocialDetails;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements GenericMapper<MemberEntity, Member> {

    /** Member Entity Except Id */
    @Override
    public MemberEntity toEntity(Member domain) {
        return MemberEntity.builder()
                .nickName(domain.memberDetails().nickName())
                .socialId(domain.socialDetails().socialId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberDetails().memberRole())
                .loginType(domain.socialDetails().loginType())
                .build();
    }

    /** Member Entity With Id */
    public MemberEntity toEntityWithId(Member domain) {
        return MemberEntity.builder()
                .id(domain.memberId().id())
                .nickName(domain.memberDetails().nickName())
                .socialId(domain.socialDetails().socialId())
                .name(domain.socialDetails().name())
                .email(domain.socialDetails().email())
                .imageUrl(domain.socialDetails().imageUrl())
                .memberRole(domain.memberDetails().memberRole())
                .loginType(domain.socialDetails().loginType())
                .createdAt(domain.memberDetails().createdAt())
                .build();
    }

    @Override
    public Member toDomain(final MemberEntity entity) {
        return Member.create(MemberId.create(entity.getId()), socialDetails(entity), memberDetails(entity));
    }

    private SocialDetails socialDetails(final MemberEntity entity) {
        return SocialDetails.create(entity.getSocialId(), entity.getLoginType(), entity.getName(), entity.getEmail(), entity.getImageUrl());
    }

    private MemberDetails memberDetails(final MemberEntity entity) {
        return MemberDetails.create(entity.getNickName(), entity.getMemberRole(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}