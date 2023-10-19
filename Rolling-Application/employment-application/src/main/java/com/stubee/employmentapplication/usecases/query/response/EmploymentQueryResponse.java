package com.stubee.employmentapplication.usecases.query.response;

import com.stubee.rollingdomains.domain.employment.consts.EmploymentStatus;

public record EmploymentQueryResponse(
        EmploymentStatus employmentStatus,

        String employerId,
        String employerName,
        String employerDescription,
        String employerAddress,
        String employerImgUrl) {}