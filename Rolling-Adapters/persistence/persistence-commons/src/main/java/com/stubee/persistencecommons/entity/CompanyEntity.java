package com.stubee.persistencecommons.entity;

import com.stubee.persistencecommons.entity.base.BaseEntity;
import com.stubee.rollingdomains.domain.company.consts.CompanyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_company")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyEntity extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 1000)
    private String imgUrl;

    private String logoUrl;

    private Integer logoRGB;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CompanyStatus companyStatus;

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
    private Long registrantId;

}