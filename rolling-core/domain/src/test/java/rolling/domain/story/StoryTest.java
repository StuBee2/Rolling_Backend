package rolling.domain.story;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rolling.domain.common.error.exception.NotMatchedIdException;
import rolling.domain.company.model.CompanyId;
import rolling.domain.member.model.MemberId;
import rolling.domain.story.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    Story.WithIdBuilder storyBuilder;

    @Test
    @DisplayName(value = "작성자일 경우 수정 성공")
    void 작성자일_경우_수정_성공() {
        EmploymentDetails employmentDetails = EmploymentDetails.builder().build();
        CorporationDetails corporationDetails = getNewCorporationDetails();
        ReviewGrades reviewGrades = getNewGrades();
        MemberId memberId = MemberId.of(1L);
        Story story = storyBuilder.build();

        story.modify(corporationDetails, employmentDetails, reviewGrades, memberId);

        assertEquals(reviewGrades, story.grades());
        assertEquals(corporationDetails, story.corporationDetails());
    }

    @Test
    @DisplayName(value = "작성자가 아닐 경우 수정 실패")
    void 작성자가_아닐_경우_수정_실패() {
        EmploymentDetails employmentDetails = EmploymentDetails.builder().build();
        CorporationDetails corporationDetails = getNewCorporationDetails();
        ReviewGrades reviewGrades = getNewGrades();
        MemberId memberId = MemberId.of(2L);

        assertThrows(
                NotMatchedIdException.class,
                () -> storyBuilder.build()
                        .modify(corporationDetails, employmentDetails, reviewGrades, memberId)
        );
    }

    private CorporationDetails getNewCorporationDetails() {
        return CorporationDetails.builder()
                .position("Product Manager")
                .welfare("매주 금요일 조기 퇴근")
                .pros("조기퇴근!")
                .cons("없음")
                .build();
    }

    private ReviewGrades getNewGrades() {
        return ReviewGrades.builder()
                .total(5.0)
                .salaryAndBenefits(5.0)
                .careerAdvancement(5.0)
                .organizationalCulture(5.0)
                .workLifeBalance(5.0)
                .build();
    }

    @BeforeEach
    void beforeEach() {
        AuthorId authorId = AuthorId.of(1L);
        storyBuilder = Story.WithIdBuilder()
                .id(StoryId.of(1L))
                .authorId(authorId)
                .companyId(CompanyId.of(1L))
                .employmentDetails(EmploymentDetails.builder()
                        .build())
                .corporationDetails(CorporationDetails.builder()
                        .position("Product Manager")
                        .welfare("매주 금요일 조기 퇴근")
                        .pros("조기퇴근!")
                        .cons("없음")
                        .build())
                .grades(ReviewGrades.builder()
                        .total(4.0)
                        .salaryAndBenefits(4.0)
                        .careerAdvancement(4.0)
                        .organizationalCulture(4.0)
                        .workLifeBalance(4.0)
                        .build())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now());
    }

}