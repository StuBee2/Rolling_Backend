package com.stubee.rollingdomains.domain.story.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.CorporationDetails;
import com.stubee.rollingdomains.domain.story.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.story.model.ReviewGrades;
import com.stubee.rollingdomains.domain.story.model.StoryId;

public interface ModifyStoryService {

    void modify(StoryId id, EmploymentDetails employmentDetails, CorporationDetails corporationDetails,
                ReviewGrades reviewGrades, MemberId memberId);

}