package com.stubee.rollingdomains.domain.company;

import com.stubee.rollingdomains.common.error.exception.NotMatchedIdException;
import com.stubee.rollingdomains.common.error.exception.WrongCalculationException;
import com.stubee.rollingdomains.domain.company.model.*;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    private Company company;

    @BeforeEach
    void init() {
        company = Company.WithIdBuilder()
                .companyId(CompanyId.of(1L))
                .companyDetails(CompanyDetails.ExceptDateBuilder()
                        .name("木ノ葉隠れの里")
                        .description("日本で最高の会社")
                        .companyLogo(CompanyLogo.of(null, null))
                        .companyAddress(Address.of("木ノ葉隠れの里", null))
                        .registrantId(RegistrantId.of(1L))
                        .build())
                .companyGrades(CompanyGrades.zero())
                .build();
    }

    @Test
    @DisplayName(value = "인자가 null일 때 CompanyDetails 생성 실패")
    void 인자가_NULL일_때_COMPANYDETAILS_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> CompanyDetails.WithDateBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> CompanyDetails.ExceptDateBuilder().build());
    }

    @Test
    @DisplayName(value = "인자가 null일 때 CompanyGrades 생성 실패")
    void 인자가_NULL일_때_COMPANYGRADES_생성_실패() {
        //when then
        assertThrows(IllegalArgumentException.class, () -> CompanyGrades.WithTotalBuilder().build());
        assertThrows(IllegalArgumentException.class, () -> CompanyGrades.ExceptTotalBuilder().build());
    }

    @Test
    @DisplayName(value = "Total 포함 CompanyGrades 생성 성공")
    void 정확한_Total일_때_생성_성공() {
        assertDoesNotThrow(() -> CompanyGrades.WithTotalBuilder()
                .total(2.5)
                .salaryAndBenefits(2.0)
                .workLifeBalance(4.0)
                .organizationalCulture(1.0)
                .careerAdvancement(3.0)
                .build());
    }

    @Test
    @DisplayName(value = "잘못 계산된 Total일 때 CompanyGrades 생성 실패")
    void 잘못_계산된_Total일_때_생성_실패() {
        assertThrows(WrongCalculationException.class, () -> CompanyGrades.WithTotalBuilder()
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
    @DisplayName(value = "오차범위를 안쪽일때 CompanyGrades 생성 실패")
    void DOUBLE_오차범위_안쪽일때_COMPANYGRADES_생성_실패() {
        assertThrows(WrongCalculationException.class, () -> CompanyGrades.WithTotalBuilder()
                .total(2.499999999999999) //오차범위
                .salaryAndBenefits(1.0)
                .workLifeBalance(2.0)
                .organizationalCulture(4.0)
                .careerAdvancement(3.0)
                .build());
    }

    @Test
    @DisplayName(value = "오차범위를 벗어났을 때 CompanyGrades 생성 성공")
    void DOUBLE_오차범위를_벗어났을_때_COMPANYGRADES_생성_성공() {
        assertDoesNotThrow(() -> CompanyGrades.WithTotalBuilder()
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
        assertDoesNotThrow(() -> company.isAuthor(memberId));
    }

    @Test
    @DisplayName(value = "Author check 실패")
    void AUTHOR_CHECK_실패() {
        //given
        MemberId memberId = MemberId.of(2L);

        //when then
        assertThrows(NotMatchedIdException.class, () -> company.isAuthor(memberId));
    }

}