package com.stubee.persistencecommons.entity;

import com.stubee.persistencecommons.entity.base.BaseEntity;
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

@Entity
@Table(name = "tbl_story")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryEntity extends BaseEntity {

    @NotNull
    @Size(max = 30)
    private String position;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String schoolLife;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String preparationCourse;

    @NotNull
    @Size(max = 100)
    private String employmentProcess;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String interviewQuestion;

    @NotNull
    @Size(max = 100)
    private String mostImportantThing;

    @NotNull
    @Size(max = 100)
    private String welfare;

    @NotNull
    @Size(max = 100)
    private String commuteTime;

    @NotNull
    @Size(max = 100)
    private String meal;

    @NotNull
    private String advantages;

    @NotNull
    private String disAdvantages;

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

}