package com.stubee.reviewpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.reviewapplication.usecases.query.StoryStatusResponse;
import com.stubee.reviewapplication.outports.query.QueryStoryPort;
import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.StoryQueryByMemberResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
class QueryStoryAdapter implements QueryStoryPort {

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