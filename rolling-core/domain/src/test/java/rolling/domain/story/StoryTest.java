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

    private Story story;
    private AuthorId authorId;

    @BeforeEach
    void init() {
        authorId = AuthorId.of(1L);
        story = Story.WithIdBuilder()
                .storyId(StoryId.of(1L))
                .storyDetails(StoryDetails.builder()
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
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build())
                .reviewGrades(ReviewGrades.builder()
                        .total(4.0)
                        .salaryAndBenefits(4.0)
                        .careerAdvancement(4.0)
                        .organizationalCulture(4.0)
                        .workLifeBalance(4.0)
                        .build())
                .build();
    }

    @Test
    @DisplayName(value = "Author 가 아닐 경우 수정 실패")
    void AUTHOR_가_아닐_경우_수정_실패() {
        final EmploymentDetails employmentDetails = EmploymentDetails.builder().build();
        final CorporationDetails corporationDetails = CorporationDetails.builder()
                        .position("Product Manager")
                        .welfare("매주 금요일 조기 퇴근")
                        .pros("조기퇴근!")
                        .cons("없음")
                        .build();
        final ReviewGrades reviewGrades = ReviewGrades.builder()
                .total(5.0)
                .salaryAndBenefits(5.0)
                .careerAdvancement(5.0)
                .organizationalCulture(5.0)
                .workLifeBalance(5.0)
                .build();
        final MemberId memberId = MemberId.of(2L);

        assertThrows(NotMatchedIdException.class, () -> story.modify(employmentDetails, corporationDetails, reviewGrades, memberId));
    }

}