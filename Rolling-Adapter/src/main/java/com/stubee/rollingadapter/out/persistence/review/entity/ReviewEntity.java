package com.stubee.rollingadapter.out.persistence.review.entity;

import com.stubee.rollingadapter.out.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "tbl_review")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull
    @Size(max = 255)
    private String position;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String careerPath;

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
    @Column(name = "fk_member_id")
    private UUID memberId;

    @NotNull
    @Column(name = "fk_company_id")
    private UUID companyId;

}