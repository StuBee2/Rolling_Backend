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

    public MemberEntity toEntityExceptId(final Member domain) {
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

    @Override
    public MemberEntity toEntity(final Member domain) {
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
        return Member.builder()
                .memberId(MemberId.create(entity.getId()))
                .socialDetails(socialDetails(entity))
                .memberDetails(memberDetails(entity))
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

    private MemberDetails memberDetails(final MemberEntity entity) {
        return MemberDetails.builder()
                .nickName(entity.getNickName())
                .memberRole(entity.getMemberRole())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}