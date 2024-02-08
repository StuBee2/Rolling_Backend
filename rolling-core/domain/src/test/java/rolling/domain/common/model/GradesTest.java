package rolling.domain.common.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rolling.domain.common.error.exception.WrongCalculationException;

import static org.junit.jupiter.api.Assertions.*;

public class GradesTest {

    @Test
    @DisplayName("생성 성공")
    void 생성_성공() {
        Grades grades = new GradesForTest(3.0, 2.5, 3.5, 3.0, 3.0);
        assertEquals(3.0, grades.getTotal());
        assertEquals(2.5, grades.getSalaryAndBenefits());
        assertEquals(3.5, grades.getWorkLifeBalance());
        assertEquals(3.0, grades.getOrganizationalCulture());
        assertEquals(3.0, grades.getCareerAdvancement());
    }

    @Test
    @DisplayName("Total 계산이 틀릴 경우 생성 실패")
    void total_계산이_틀릴_경우_생성_실패() {
        assertThrows(
                WrongCalculationException.class,
                () -> new GradesForTest(3.5, 2.5, 3.5, 3.0, 3.0)
        );
    }

    @Test
    @DisplayName("Total없이 생성 성공")
    void total_없이_생성_성공() {
        Grades grades = new GradesForTest(2.5, 3.5, 3.0, 3.0);

        assertEquals(3.0, grades.getTotal());
        assertEquals(2.5, grades.getSalaryAndBenefits());
        assertEquals(3.5, grades.getWorkLifeBalance());
        assertEquals(3.0, grades.getOrganizationalCulture());
        assertEquals(3.0, grades.getCareerAdvancement());
    }

    @Test
    @DisplayName("equals(), hashCode() 테스트")
    void equalsAndHashCode_테스트() {
        Grades grades1 = new GradesForTest(3.0, 2.5, 3.5, 3.0, 3.0);
        Grades grades2 = new GradesForTest(3.0, 2.5, 3.5, 3.0, 3.0);

        assertEquals(grades1, grades2);
        assertEquals(grades1.hashCode(), grades2.hashCode());
    }

    static class GradesForTest extends Grades {

        GradesForTest(Double total, Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
            super(total, salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
        }

        GradesForTest(Double salaryAndBenefits, Double workLifeBalance, Double organizationalCulture, Double careerAdvancement) {
            super(salaryAndBenefits, workLifeBalance, organizationalCulture, careerAdvancement);
        }

    }

}