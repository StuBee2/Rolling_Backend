package com.stubee.reviewpersistence;

import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.story.model.*;

public abstract class StoryTestUtils {

    public static Story create(String pros, String cons, String welfare, String position, ReviewGrades reviewGrades) {
        return Story.ExceptIdBuilder()
                .storyDetails(StoryDetails.ExceptDateBuilder()
                        .authorId(AuthorId.of(1L))
                        .companyId(CompanyId.of(100L))
                        .corporationDetails(CorporationDetails.builder()
                                .pros(pros)
                                .cons(cons)
                                .welfare(welfare)
                                .position(position)
                                .build())
                        .employmentDetails(EmploymentDetails.builder()
                                .build())
                        .build())
                .reviewGrades(reviewGrades)
                .build();
    }



}