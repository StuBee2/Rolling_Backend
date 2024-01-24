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

    Member.WithIdBuilder memberBuilder;
    MemberService memberService;

    @BeforeEach
    void init() {
        memberBuilder = Member.WithIdBuilder()
                .id(MemberId.of(1L))
                .role(MemberRole.ADMIN)
                .details(new MemberDetails("suzzing", "최수원", "test@gmail.com", null))
                .socialDetails(new SocialDetails("1234567", "suw0n", LoginType.GITHUB))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now());
        memberService = new MemberServiceForException();
    }

    @Test
    @DisplayName(value = "중복된 Nickname일 경우 수정 실패")
    void 중복된_NICKNAME_일_경우_예외_발생() {
        String newNickname = "suzzing9999";

        assertThrows(
                DuplicatedNicknameException.class,
                () -> memberBuilder.build()
                        .modify(newNickname, memberService)
        );
    }

}