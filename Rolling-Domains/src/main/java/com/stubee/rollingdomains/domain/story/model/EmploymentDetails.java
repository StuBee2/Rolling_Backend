package com.stubee.rollingdomains.domain.story.model;

import lombok.Builder;

public record EmploymentDetails(
        String schoolLife, //학교에서의 생활
        String preparationCourse, //취업준비과정
        String employmentProcess, //채용 프로세스
        String interviewQuestion, //면접질문
        String mostImportantThing /*가장 중요하는 점*/) {
    @Builder
    public EmploymentDetails {
    }
}