package rolling.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.domain.member.exception.DuplicatedNicknameException;
import rolling.domain.member.exception.WrongLoginTypeException;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberDetails;
import rolling.domain.member.model.MemberId;
import rolling.domain.member.model.SocialDetails;
import rolling.domain.member.service.MemberService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    Member.WithIdBuilder memberBuilder;
    MemberService memberService;

    @Test
    @DisplayName(value = "중복되지 않은 닉네임일 경우 수정 성공")
    void 중복되지_않은_닉네임일_경우_수정_성공() {
        String newNickname = "suzzing9999";
        Member member = memberBuilder.build();

        member.modify(newNickname, memberService);

        assertEquals(newNickname, member.getDetails().nickName());
    }

    @Test
    @DisplayName(value = "중복된 닉네임일 경우 수정 실패")
    void 중복된_닉네임일_경우_수정_실패() {
        memberService = new MemberServiceForFailure();

        String newNickname = "suzzing9999";

        assertThrows(
                DuplicatedNicknameException.class,
                () -> memberBuilder.build()
                        .modify(newNickname, memberService)
        );
    }

    @Test
    @DisplayName(value = "로그인 타입이 일치할 경우 수정 성공")
    void 로그인_타입이_일치할_경우_수정_성공() {
        String newSocialLoginId = "1s1u1z1z1i1n1g191";
        Member member = memberBuilder.build();

        member.modify(newSocialLoginId, LoginType.GITHUB);

        assertEquals(newSocialLoginId, member.getSocialDetails().socialLoginId());
    }

    @Test
    @DisplayName(value = "로그인 타입이 일치하지 않을 경우 수정 실패")
    void 로그인_타입이_일치하지_않을_경우_수정_실패() {
        String newSocialLoginId = "1s1u1z1z1i1n1g191";

        assertThrows(
                WrongLoginTypeException.class,
                () -> memberBuilder.build()
                        .modify(newSocialLoginId, LoginType.GOOGLE)
        );
    }

    @BeforeEach
    void beforeEach() {
        memberBuilder = Member.WithIdBuilder()
                .id(MemberId.of(1L))
                .role(MemberRole.ADMIN)
                .details(new MemberDetails("suzzing", "최수원", "test@gmail.com", null))
                .socialDetails(new SocialDetails("1234567", "suw0n", LoginType.GITHUB))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now());
        memberService = new MemberServiceForSuccess();
    }

}