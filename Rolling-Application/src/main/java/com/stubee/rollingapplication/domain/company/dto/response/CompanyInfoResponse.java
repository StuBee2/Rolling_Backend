package com.stubee.rollingapplication.domain.company.dto.response;

import com.stubee.rollingapplication.domain.review.dto.response.ReviewInfoResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record CompanyInfoResponse(
        CompanyQueryResponse companyQueryResponse,
        List<ReviewInfoResponse> reviewInfoList) {}