package rolling.jpamysql.member;

import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.MemberId;
import rolling.domain.member.model.SocialDetails;

abstract class MemberMapper {

    static MemberJPAEntity toEntity(final Member domain) {
        return MemberJPAEntity.builder()
                .memberRole(domain.role())
                .nickName(domain.details().nickName())
                .name(domain.details().name())
                .email(domain.details().email())
                .imageUrl(domain.details().imageUrl())
                .socialId(domain.socialDetails().socialId())
                .socialLoginId(domain.socialDetails().socialLoginId())
                .loginType(domain.socialDetails().loginType())
                .build();
    }

    static MemberJPAEntity toEntityWithId(final Member domain) {
        return MemberJPAEntity.builder()
                .id(domain.id().getId())
                .nickName(domain.details().nickName())
                .socialId(domain.socialDetails().socialId())
                .socialLoginId(domain.socialDetails().socialLoginId())
                .name(domain.details().name())
                .email(domain.details().email())
                .imageUrl(domain.details().imageUrl())
                .memberRole(domain.role())
                .loginType(domain.socialDetails().loginType())
                .createdAt(domain.createdAt())
                .modifiedAt(domain.modifiedAt())
                .build();
    }

    static Member toDomain(final MemberJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Member.WithIdBuilder()
                .id(MemberId.of(entity.getId()))
                .role(entity.getMemberRole())
                .socialDetails(new SocialDetails(entity.getSocialId(), entity.getSocialLoginId(), entity.getLoginType()))
                .details(new MemberDetails(entity.getNickName(), entity.getName(), entity.getEmail(), entity.getImageUrl()))
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}