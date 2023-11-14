package com.stubee.rollingdomains.domain.member;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.rollingdomains.domain.member.exception.WrongLoginTypeException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.model.MemberDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.model.SocialDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member member;

    @BeforeEach
    void init() {
        member = Member.WithIdBuilder()
                .memberId(MemberId.of(1L))
                .memberDetails(MemberDetails.AllArgsBuilder()
                        .memberRole(MemberRole.MEMBER)
                        .nickName("hello")
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build())
                .socialDetails(SocialDetails.AllArgsBuilder()
                        .socialId("1111111111")
                        .socialLoginId("suw0n")
                        .loginType(LoginType.GITHUB)
                        .name("최수원")
                        .build())
                .build();
    }

    @Test
    @DisplayName(value = "VO가 null일 때 Member 생성 샐패")
    void VO가_NULL일_때_MEMBER_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> Member.ExceptIdBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> Member.WithIdBuilder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 MemberDetails 생성 실패")
    void 인자가_NULL일_때_MEMBERDETAILS_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> MemberDetails.OnlyWithRoleBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> MemberDetails.AllArgsBuilder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 SocialDetails 생성 실패")
    void 인자가_NULL일_때_SOCIALDETAILS_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> SocialDetails.AllArgsBuilder().build());
    }

    @Test
    @DisplayName(value = "LoginType이 다를 때 Exception 발생")
    void LOGIN_TYPE이_다를_때_EXCEPTION_발생() {
        //given
        final LoginType loginType = LoginType.GOOGLE;

        //when then
        assertThrows(WrongLoginTypeException.class, () -> member.isEqualLoginType(loginType));
    }

    @Test
    @DisplayName(value = "LoginType이 같을 때 성공")
    void LOGIN_TYPE이_같을_때_성공() {
        //given
        final LoginType loginType = LoginType.GITHUB;

        //when then
        assertDoesNotThrow(() -> member.isEqualLoginType(loginType));
    }

    @Test
    @DisplayName(value = "LoginId 변경 성공")
    void LOGIN_ID_변경_성공() {
        //given
        final String newLoginId = "suw0n222";

        //when
        final String changedLoginId = member.updateLoginId(newLoginId).socialDetails().socialLoginId();

        //then
        assertEquals(newLoginId, changedLoginId);
    }

    @Test
    @DisplayName(value = "Nickname 변경 성공")
    void NICKNAME_변경_성공() {
        //given
        final String newNickname = "Uchiha Itachi";

        //when
        final String changedNickname = member.updateNickname(newNickname).memberDetails().nickName();

        //then
        assertEquals(newNickname, changedNickname);
    }

    @Test
    @DisplayName(value = "MemberRole 상승 성공")
    void MEMBER_ROLE_상승_성공() {
        //given

        //when
        final MemberRole elevatedRole = member.elevateToMember().memberDetails().memberRole();

        //then
        assertEquals(MemberRole.MEMBER, elevatedRole);
    }

}