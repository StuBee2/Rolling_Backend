package com.stubee.rollingdomains.domain.story;

import com.stubee.rollingdomains.common.error.exception.NotMatchedIdException;
import com.stubee.rollingdomains.common.error.exception.WrongCalculationException;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    private Story story;

    @BeforeEach
    void init() {
        story = Story.WithIdBuilder()
                .storyId(StoryId.of(1L))
                .storyDetails(StoryDetails.WithDateBuilder()
                        .authorId(AuthorId.of(1L))
                        .companyId(CompanyId.of(1L))
                        .corporationDetails(CorporationDetails.builder()
                                .position("Project Manager")
                                .pros("어려운 과제가 많아 성장할 수 있습니다.")
                                .cons("일이 많습니다ㅜㅜ")
                                .welfare("쏘쏘합니다.")
                                .build())
                        .employmentDetails(EmploymentDetails.builder()
                                .build())
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build())
                .reviewGrades(ReviewGrades.ExceptTotalBuilder()
                        .salaryAndBenefits(3.0)
                        .workLifeBalance(2.0)
                        .organizationalCulture(3.0)
                        .careerAdvancement(5.0)
                        .build())
                .build();
    }

    @Test
    @DisplayName(value = "VO가 null일 때 생성 실패")
    void VO가_NULL일_때_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> Story.WithIdBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> Story.ExceptIdBuilder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 StoryDetails 생성 실패")
    void 인자가_NULL일_때_STORYDETAILS_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> StoryDetails.ExceptDateBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> StoryDetails.WithDateBuilder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 CorporationDetails 생성 실패")
    void 인자가_NULL일_때_CORPORATIONDETAILS_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> CorporationDetails.builder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 ReviewGrades 생성 실패")
    void 인자가_NULL일_때_REVIEWGRADES_생성_실패() {
        assertThrows(IllegalArgumentException.class, () -> ReviewGrades.WithTotalBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> ReviewGrades.ExceptTotalBuilder().build());
    }

    @Test
    @DisplayName(value = "Total 포함 Grades ReviewGrades 생성 성공")
    void 정확한_Total일_때_REVIEWGRADES_생성_성공() {
        assertDoesNotThrow(() -> ReviewGrades.WithTotalBuilder()
                .total(2.5)
                .salaryAndBenefits(2.0)
                .workLifeBalance(4.0)
                .organizationalCulture(1.0)
                .careerAdvancement(3.0)
                .build());
    }

    @Test
    @DisplayName(value = "잘못 계산된 Total일 때 ReviewGrades 생성 실패")
    void 잘못_계산된_Total일_때_REVIEWGRADES_생성_실패() {
        assertThrows(WrongCalculationException.class, () -> ReviewGrades.WithTotalBuilder()
                .total(2.3)
                .salaryAndBenefits(1.0)
                .workLifeBalance(2.0)
                .organizationalCulture(4.0)
                .careerAdvancement(3.0)
                .build());
    }

    /*
     * Double 오차범위 : 0.000000000000001
     * */
    @Test
    @DisplayName(value = "Double 오차범위를 안쪽일때 ReviewGrades 생성 실패")
    void DOUBLE_오차범위_안쪽일때_REVIEWGRADES_생성_실패() {
        assertThrows(WrongCalculationException.class, () -> ReviewGrades.WithTotalBuilder()
                .total(2.499999999999999) //오차범위
                .salaryAndBenefits(1.0)
                .workLifeBalance(2.0)
                .organizationalCulture(4.0)
                .careerAdvancement(3.0)
                .build());
    }

    @Test
    @DisplayName(value = "Double 오차범위를 벗어났을 때 ReviewGrades 생성 성공")
    void DOUBLE_오차범위를_벗어났을_때_REVIEWGRADES_생성_성공() {
        assertDoesNotThrow(() -> ReviewGrades.WithTotalBuilder()
                .total(2.4999999999999999)
                .salaryAndBenefits(1.0)
                .workLifeBalance(2.0)
                .organizationalCulture(4.0)
                .careerAdvancement(3.0)
                .build());
    }

    @Test
    @DisplayName(value = "Author check 성공")
    void AUTHOR_CHECK_성공() {
        //given
        MemberId memberId = MemberId.of(1L);

        //when then
        assertDoesNotThrow(() -> story.isAuthor(memberId));
    }

    @Test
    @DisplayName(value = "Author check 실패")
    void AUTHOR_CHECK_실패() {
        //given
        MemberId memberId = MemberId.of(2L);

        //when then
        assertThrows(NotMatchedIdException.class, () -> story.isAuthor(memberId));
    }

}