package com.stubee.rollingcore.common.model;

import lombok.Builder;

@Builder
public record Grades(
        Double totalGrade,
        Double balanceGrade,
        Double salaryGrade,
        Double welfareGrade) {}