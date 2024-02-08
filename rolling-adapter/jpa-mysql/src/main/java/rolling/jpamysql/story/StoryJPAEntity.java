package rolling.jpamysql.story;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import rolling.jpamysql.common.entity.base.BaseTSIDEntity;

@Entity
@Table(name = "tbl_story")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryJPAEntity extends BaseTSIDEntity {

    @NotNull
    @Size(max = 30)
    private String position;

    @NotNull
    @Size(max = 100)
    private String welfare;

    @Size(max = 100)
    private String commuteTime;

    @Size(max = 100)
    private String meal;

    @NotNull
    private String advantages;

    @NotNull
    private String disAdvantages;

    @Column(columnDefinition = "TEXT")
    private String etc;

    @NotNull
    private Double totalGrade;

    @NotNull
    private Double salaryAndBenefits;

    @NotNull
    private Double workLifeBalance;

    @NotNull
    private Double organizationalCulture;

    @NotNull
    private Double careerAdvancement;

    @NotNull
    private Long memberId;

    @NotNull
    private Long companyId;

    //
    @Column(columnDefinition = "TEXT")
    private String schoolLife;

    @Column(columnDefinition = "TEXT")
    private String preparationCourse;

    @Size(max = 100)
    private String employmentProcess;

    @Column(columnDefinition = "TEXT")
    private String interviewQuestion;

    @Size(max = 100)
    private String mostImportantThing;

}