package com.stubee.memberpersistence;

import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;

import static org.junit.jupiter.api.Assertions.*;

public abstract class MemberTestUtils {

    public static Member createDomain(String socialId, String socialLoginId, LoginType loginType, String email, String name, String imageUrl) {
        return Member.ExceptIdBuilder()
                .memberDetails(MemberDetails.OnlyWithRoleBuilder()
                        .memberRole(MemberRole.TEMP)
                        .build())
                .socialDetails(SocialDetails.AllArgsBuilder()
                        .socialId(socialId)
                        .socialLoginId(socialLoginId)
                        .loginType(loginType)
                        .email(email)
                        .name(name)
                        .imageUrl(imageUrl)
                        .build())
                .build();
    }

    public static MemberEntity createEntity(String socialId, String socialLoginId, LoginType loginType, String email, String name, String imageUrl) {
        return MemberEntity.builder()
                .memberRole(MemberRole.TEMP)
                .socialId(socialId)
                .socialLoginId(socialLoginId)
                .loginType(loginType)
                .email(email)
                .name(name)
                .imageUrl(imageUrl)
                .build();
    }

    public static void isEqual(Member domain, MemberEntity entity) {
        assertEquals(domain.memberDetails().memberRole(), entity.getMemberRole());
        assertEquals(domain.memberDetails().nickName(), entity.getNickName());

        assertEquals(domain.socialDetails().socialId(), entity.getSocialId());
        assertEquals(domain.socialDetails().socialLoginId(), entity.getSocialLoginId());
        assertEquals(domain.socialDetails().loginType(), entity.getLoginType());
    }

}