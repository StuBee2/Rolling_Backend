package rolling.domain.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rolling.domain.common.error.exception.NotMatchedIdException;
import rolling.domain.company.CompanyServiceForFailure;
import rolling.domain.company.CompanyServiceForSuccess;
import rolling.domain.company.consts.CompanyStatus;
import rolling.domain.company.exception.DuplicatedCompanyNameException;
import rolling.domain.company.model.*;
import rolling.domain.company.service.CompanyService;
import rolling.domain.member.model.MemberId;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    Company.WithIdBuilder companyBuilder;
    CompanyService companyService;

    @Test
    @DisplayName("중복되지 않은 이름일 경우 수정 성공")
    void 중복되지_않은_이름일_경우_수정_성공() {
        MemberId memberId = MemberId.of(1L);
        String newName = "광소고";
        Company company = companyBuilder.build();

        company.modify(memberId, newName, companyService);

        assertEquals(newName, company.details().name());
    }

    @Test
    @DisplayName("등록자가 아닐 경우 예외 발생")
    void 등록자_가_아닐_경우_수정_실패() {
        MemberId memberId = MemberId.of(2L);
        String name = "광소고";

        assertThrows(
                NotMatchedIdException.class,
                () -> companyBuilder.build()
                        .modify(memberId, name, companyService)
        );
    }

    @Test
    @DisplayName("중복된 이름일 경우 수정 실패")
    void 중복된_이름일_경우_수정_실패() {
        MemberId memberId = MemberId.of(1L);
        String name = "광소고";
        companyService = new CompanyServiceForFailure();

        assertThrows(
                DuplicatedCompanyNameException.class,
                () -> companyBuilder.build()
                        .modify(memberId, name, companyService)
        );
    }

    @BeforeEach
    void beforeEach() {
        companyBuilder = Company.WithIdBuilder()
                .id(CompanyId.of(1L))
                .status(CompanyStatus.ACCEPTED)
                .registrantId(RegistrantId.of(1L))
                .details(new CompanyDetails("대소고", "고등학교", Address.of("대구"), null))
                .grades(CompanyGrades.ExceptTotalBuilder()
                        .salaryAndBenefits(4.0)
                        .careerAdvancement(4.0)
                        .workLifeBalance(4.5)
                        .organizationalCulture(3.5)
                        .build())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now());
        companyService = new CompanyServiceForSuccess();
    }

}