package rolling.jpamysql.member;

import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.MemberId;
import rolling.domain.member.model.SocialDetails;

abstract class MemberMapper {

    static MemberJPAEntity toEntity(final Member domain) {
        return MemberJPAEntity.builder()
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

    static MemberJPAEntity toEntityWithId(final Member domain) {
        return MemberJPAEntity.builder()
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

    static Member toDomain(final MemberJPAEntity entity) {
        if(entity==null) {
            return null;
        }

        return Member.WithIdBuilder()
                .memberId(MemberId.of(entity.getId()))
                .socialDetails(socialDetails(entity))
                .memberDetails(memberDetails(entity))
                .build();
    }

    private static SocialDetails socialDetails(final MemberJPAEntity entity) {
        return SocialDetails.builder()
                .socialId(entity.getSocialId())
                .socialLoginId(entity.getSocialLoginId())
                .loginType(entity.getLoginType())
                .name(entity.getName())
                .email(entity.getEmail())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    private static MemberDetails memberDetails(final MemberJPAEntity entity) {
        return new MemberDetails(entity.getNickName(), entity.getMemberRole(), entity.getCreatedAt(), entity.getModifiedAt());
    }

}