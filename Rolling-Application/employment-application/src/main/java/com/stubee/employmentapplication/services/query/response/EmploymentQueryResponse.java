package com.stubee.employmentapplication.services.query.response;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;

import java.util.UUID;

public record EmploymentQueryResponse(
        EmploymentStatus employmentStatus,

        UUID employerId,
        String employerName,
        String employerDescription,
        String employerAddress,
        String employerImgUrl) {}