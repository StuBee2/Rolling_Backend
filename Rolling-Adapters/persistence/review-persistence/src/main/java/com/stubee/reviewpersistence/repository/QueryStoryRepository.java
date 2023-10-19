package com.stubee.reviewpersistence.repository;

import com.stubee.reviewapplication.usecases.query.response.StoryStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.persistencecommons.entity.StoryEntity;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;

import java.util.List;

public interface QueryStoryRepository {

    StoryEntity findById(Long reviewId);

    StoryQueryByCompanyResponse findInfoById(Long reviewId);

    StoryStatusResponse findStatusByMemberId(Long memberId);

    List<StoryQueryByMemberResponse> findByMemberId(Long memberId, PageRequest pageRequest);

    List<StoryQueryByCompanyResponse> findByCompanyId(Long companyId, PageRequest pageRequest);

    List<StoryEntity> findAll(PageRequest pageRequest);

}