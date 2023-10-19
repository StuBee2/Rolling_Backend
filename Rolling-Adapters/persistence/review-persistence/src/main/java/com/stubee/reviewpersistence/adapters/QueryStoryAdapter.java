package com.stubee.reviewpersistence.adapters;

import com.stubee.reviewapplication.usecases.query.response.StoryStatusResponse;
import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.persistencecommons.annotations.Adapter;
import com.stubee.reviewapplication.outports.query.QueryStoryPort;
import com.stubee.reviewpersistence.mapper.StoryMapper;
import com.stubee.reviewpersistence.repository.QueryStoryRepository;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.response.StoryQueryByMemberResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class QueryStoryAdapter implements QueryStoryPort {

    private final QueryStoryRepository queryStoryRepository;
    private final StoryMapper storyMapper;

    @Override
    public Optional<Story> findById(final Long reviewId) {
        return Optional.ofNullable(storyMapper.toDomain(queryStoryRepository.findById(reviewId)));
    }

    @Override
    public Optional<StoryQueryByCompanyResponse> findInfoById(final Long reviewId) {
        return Optional.ofNullable(queryStoryRepository.findInfoById(reviewId));
    }

    @Override
    public List<StoryQueryByMemberResponse> findByMemberId(final Long memberId, PageRequest pageRequest) {
        return queryStoryRepository.findByMemberId(memberId, pageRequest);
    }

    @Override
    public List<StoryQueryByCompanyResponse> findByCompanyId(final Long companyId, PageRequest pageRequest) {
        return queryStoryRepository.findByCompanyId(companyId, pageRequest);
    }

    @Override
    public StoryStatusResponse getStatusByMemberId(final Long memberId) {
        return queryStoryRepository.findStatusByMemberId(memberId);
    }

}