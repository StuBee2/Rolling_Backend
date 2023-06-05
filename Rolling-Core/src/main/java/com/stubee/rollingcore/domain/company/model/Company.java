package com.stubee.rollingcore.domain.company.model;

import com.stubee.rollingcore.common.model.Grades;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Company(
        UUID id,
        CompanyDetails companyDetails,
        Grades companyGrades,
        UUID registrantId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}