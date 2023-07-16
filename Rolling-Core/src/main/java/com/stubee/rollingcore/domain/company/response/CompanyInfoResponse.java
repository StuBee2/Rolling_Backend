package com.stubee.rollingcore.domain.company.response;

import com.stubee.rollingcore.domain.review.response.ReviewInfoResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record CompanyInfoResponse(
        CompanyQueryResponse companyQueryResponse,
        List<ReviewInfoResponse> reviewInfoList) {}