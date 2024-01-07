package rolling.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.exception.DuplicatedNicknameException;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.MemberId;
import rolling.domain.member.model.SocialDetails;
import rolling.domain.member.service.MemberService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member member;
    private MemberService memberService;

    @BeforeEach
    void init() {
        member = Member.WithIdBuilder()
                .memberId(MemberId.of(1L))
                .memberDetails(new MemberDetails("suzzing", MemberRole.ADMIN, LocalDateTime.now(), LocalDateTime.now()))
                .socialDetails(SocialDetails.builder()
                        .socialId("123456789")
                        .socialLoginId("suw0n")
                        .loginType(LoginType.GITHUB)
                        .email("test@gmail.com")
                        .imageUrl("rolling.kr")
                        .build())
                .build();
        memberService = new FakeMemberService();
    }

    @Test
    @DisplayName(value = "중복된 Nickname 일 경우 수정 실패")
    void 중복된_NICKNAME_일_경우_수정_실패() {
        final String newNickname = "suzzing9999";

        assertThrows(DuplicatedNicknameException.class, () -> member.modifyNickname(newNickname, memberService));
    }

}