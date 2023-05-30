package com.stubee.rollingadapter.out.persistence.company.entity;

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

    @NotNull
    private Double totalGrade;

    @NotNull
    private Double balanceGrade;

    @NotNull
    private Double salaryGrade;

    @NotNull
    private Double welfareGrade;

    @NotNull
    @Column(name = "fk_registrant_id")
    private UUID registrantId;

}