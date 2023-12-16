package com.stubee.reviewpersistence.adapters;

import com.stubee.reviewapplication.usecases.query.StoryStatusResponse;
import com.stubee.persistencecommons.entity.StoryEntity;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;
import com.stubee.rollingdomains.common.model.PageRequest;

import java.util.List;

interface QueryStoryRepository {

    StoryEntity findById(Long reviewId);

    StoryQueryByCompanyResponse findInfoById(Long reviewId);

    StoryStatusResponse findStatusByMemberId(Long memberId);

    List<StoryQueryByMemberResponse> findByMemberId(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompanyId(Long companyId, PageRequest pageRequest);

    List<StoryEntity> findAll(PageRequest pageRequest);

}