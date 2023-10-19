package com.stubee.rollingdomains.domain.story.model;

import com.stubee.rollingdomains.common.error.Assert;
import lombok.Builder;

public record EmploymentDetails(
        String position, //포지션
        String schoolLife, //학교에서의 생활
        String preparationCourse, //취업준비과정
        String employmentProcess, //채용 프로세스
        String interviewQuestion, //면접질문
        String mostImportantThing /*가장 중요하는 점*/) {
    @Builder
    public EmploymentDetails {
        Assert.notNull(position, "Position must not be null");
        Assert.notNull(schoolLife, "SchoolLife must not be null");
        Assert.notNull(preparationCourse, "PreparationCourse must not be null");
        Assert.notNull(employmentProcess, "EmploymentProcess must not be null");
        Assert.notNull(interviewQuestion, "InterviewQuestion must not be null");
        Assert.notNull(mostImportantThing, "MostImportantThing must not be null");
    }
}