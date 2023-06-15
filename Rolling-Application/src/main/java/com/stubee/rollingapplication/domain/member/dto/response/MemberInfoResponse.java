package com.stubee.rollingapplication.domain.member.dto.response;

import com.stubee.rollingapplication.domain.review.dto.response.ReviewQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberInfoResponse(
        Member member,
        List<Company> companyList,
        List<ReviewQueryResponse> reviewList) {}