package com.stubee.reviewpersistence;

import com.stubee.persistencecommons.PersistenceAdapterTest;
import com.stubee.reviewpersistence.adapters.CommandStoryAdapter;
import com.stubee.reviewpersistence.mapper.StoryMapper;
import com.stubee.reviewpersistence.repository.CommandStoryJpaRepository;
import com.stubee.rollingdomains.domain.story.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
@ContextConfiguration(classes = {CommandStoryAdapter.class, StoryMapper.class, CommandStoryJpaRepository.class})
public class CommandStoryAdapterTest {

    @Autowired
    private CommandStoryAdapter commandStoryAdapter;

    @Autowired
    private CommandStoryJpaRepository storyJpaRepository;

    @Test
    @DisplayName(value = "Story 생성 성공")
    void STORY_생성_성공() {
        //given
        String pros = "연봉이 높습니다";
        String cons = "근처에 맛집이 없습니다";
        String welfare = "회사 근처 지역 거주시 매달 50만원 지원";
        String position = "백엔드 개발자";
        ReviewGrades reviewGrades = ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(5.0)
                .workLifeBalance(5.0)
                .organizationalCulture(5.0)
                .careerAdvancement(5.0)
                .build();
        Story story = StoryTestUtils.create(pros, cons, welfare, position, reviewGrades);

        //when
        StoryId id = commandStoryAdapter.save(story)
                .storyId();

        //then
        assertTrue(storyJpaRepository.existsById(id.getId()));
    }

    @Test
    @DisplayName(value = "Story 삭제 성공")
    void STORY_삭제_성공() {
        //given
        String pros = "연봉이 높습니다";
        String cons = "근처에 맛집이 없습니다";
        String welfare = "회사 근처 지역 거주시 매달 50만원 지원";
        String position = "백엔드 개발자";
        ReviewGrades reviewGrades = ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(5.0)
                .workLifeBalance(5.0)
                .organizationalCulture(5.0)
                .careerAdvancement(5.0)
                .build();
        StoryId id = commandStoryAdapter.save(StoryTestUtils.create(pros, cons, welfare, position, reviewGrades))
                .storyId();

        //when
        commandStoryAdapter.deleteById(id);

        //then
        assertFalse(storyJpaRepository.existsById(id.getId()));
    }

    @Test
    @DisplayName(value = "Story 수정 성공")
    void STORY_수정_성공() {
        //given
        String pros = "연봉이 높습니다";
        String cons = "근처에 맛집이 없습니다";
        String welfare = "회사 근처 지역 거주시 매달 50만원 지원";
        String position = "백엔드 개발자";
        ReviewGrades reviewGrades = ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(5.0)
                .workLifeBalance(5.0)
                .organizationalCulture(5.0)
                .careerAdvancement(5.0)
                .build();

        Story savedStory = commandStoryAdapter.save(StoryTestUtils.create(pros, cons, welfare, position, reviewGrades));

        ReviewGrades modifiedReviewGrades = ReviewGrades.ExceptTotalBuilder()
                .salaryAndBenefits(4.0)
                .workLifeBalance(4.0)
                .organizationalCulture(4.0)
                .careerAdvancement(4.0)
                .build();
        CorporationDetails modifiedCorporationDetails = CorporationDetails.builder()
                .pros(pros)
                .cons(cons)
                .welfare(welfare)
                .position(position)
                .build();

        //when
        Story modifiedStory = commandStoryAdapter.update(savedStory.update(
                savedStory.storyDetails().employmentDetails(),
                modifiedCorporationDetails,
                modifiedReviewGrades));

        //then
        assertEquals(modifiedStory.reviewGrades(), modifiedReviewGrades);
        assertEquals(modifiedStory.storyDetails().corporationDetails(), modifiedCorporationDetails);
    }

}