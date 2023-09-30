package com.stubee.memberpersistence.mapper;

import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.persistencecommons.annotations.DomainObjectMapper;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;

@DomainObjectMapper
public class MemberMapper implements com.stubee.persistencecommons.mapper.DomainObjectMapper<MemberEntity, Member> {

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

        return Member.WithIdBuilder()
                .memberId(MemberId.of(entity.getId()))
                .socialDetails(socialDetails(entity))
                .memberDetails(memberDetails(entity))
                .build();
    }

    private SocialDetails socialDetails(final MemberEntity entity) {
        return SocialDetails.AllArgsBuilder()
                .socialId(entity.getSocialId())
                .socialLoginId(entity.getSocialLoginId())
                .loginType(entity.getLoginType())
                .name(entity.getName())
                .email(entity.getEmail())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    private MemberDetails memberDetails(final MemberEntity entity) {
        return MemberDetails.AllArgsBuilder()
                .nickName(entity.getNickName())
                .memberRole(entity.getMemberRole())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}