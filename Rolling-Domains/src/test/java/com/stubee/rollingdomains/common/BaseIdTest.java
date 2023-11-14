package com.stubee.rollingdomains.common;

import com.stubee.rollingdomains.common.error.exception.NotMatchedIdException;
import com.stubee.rollingdomains.domain.company.model.RegistrantId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.AuthorId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseIdTest {

    @Test
    @DisplayName(value = "같은 Id일 때 성공")
    void 같은_ID일_때_성공() {
        //given
        MemberId memberId = MemberId.of(1L);
        RegistrantId registrantId = RegistrantId.of(1L);
        AuthorId authorId = AuthorId.of(1L);

        //then
        assertDoesNotThrow(() -> authorId.isEqual(memberId));
        assertDoesNotThrow(() -> memberId.isEqual(registrantId));
        assertDoesNotThrow(() -> registrantId.isEqual(authorId));
    }

    @Test
    @DisplayName(value = "다른 Id일 때 실패")
    void 다른_ID일_때_실패() {
        //given
        MemberId memberId = MemberId.of(1L);
        RegistrantId registrantId = RegistrantId.of(2L);
        AuthorId authorId = AuthorId.of(3L);

        //then
        assertThrows(NotMatchedIdException.class, () -> authorId.isEqual(memberId));
        assertThrows(NotMatchedIdException.class, () -> memberId.isEqual(registrantId));
        assertThrows(NotMatchedIdException.class, () -> registrantId.isEqual(authorId));
    }

}