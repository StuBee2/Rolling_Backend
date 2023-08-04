package com.stubee.rollingcore.domain.employment.response;

import com.stubee.rollingcore.domain.employment.enums.EmploymentStatus;

import java.util.UUID;

public record EmploymentQueryResponse(
        EmploymentStatus employmentStatus,

        UUID employerId,
        String employerName,
        String employerDescription,
        String employerAddress,
        String employerImgUrl) {}