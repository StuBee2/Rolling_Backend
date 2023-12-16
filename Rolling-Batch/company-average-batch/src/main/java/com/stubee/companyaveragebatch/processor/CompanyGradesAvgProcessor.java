package com.stubee.companyaveragebatch.processor;

import com.stubee.rollingdomains.common.model.PageRequest;
import com.stubee.batchcommons.annotations.Processor;
import com.stubee.reviewapplication.usecases.query.StoryQueryByCompanyResponse;
import com.stubee.reviewapplication.usecases.query.QueryStoryInfoListByCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.CompanyGrades;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

import static com.stubee.companyaveragebatch.job.consts.BatchConstants.PAGE_SIZE;

@Processor
@Slf4j
@RequiredArgsConstructor
public class CompanyGradesAvgProcessor implements ItemProcessor<List<Company>, List<Company>> {

    private final QueryStoryInfoListByCompanyUseCase queryReviewInfoListByCompanyUseCase;

    private double salaryAndBenefitsSum;
    private double workLifeBalanceSum;
    private double organizationalCultureSum;
    private double careerAdvancementSum;
    private long reviewPage;
    private long reviewCnt;

    @Override
    public List<Company> process(final List<Company> readCompanyList) {
        log.info("-----Processor Start-----");

        final List<Company> processedCompanyList = new ArrayList<>();

        readCompanyList.forEach(company -> {
            this.init();

            log.info("Company Name : {}", company.companyDetails().name());

            this.calculateSums(company.companyId().getId());

            final CompanyGrades updatedGrades = this.calculateAverages();

            final Company updatedCompany = company.update(updatedGrades);

            processedCompanyList.add(updatedCompany);
        });

        log.info("-----Processor End-----");

        return processedCompanyList;
    }

    private void calculateSums(final Long companyId) {
        while (true) {
            final List<StoryQueryByCompanyResponse> reviewList = queryReviewInfoListByCompanyUseCase.get(companyId,
                    PageRequest.of(reviewPage, PAGE_SIZE)).data();

            log.info("ReviewList Size : {}", reviewList.size());

            reviewList.forEach(review -> {
                salaryAndBenefitsSum += review.salaryAndBenefits();
                workLifeBalanceSum += review.workLifeBalance();
                organizationalCultureSum += review.organizationalCulture();
                careerAdvancementSum += review.careerAdvancement();
            });

            reviewCnt += reviewList.size();
            reviewPage++;

            if (reviewList.size() < PAGE_SIZE) {
                break;
            }
        }
    }

    private CompanyGrades calculateAverages() {
        final double salaryAndBenefitsAvg = calculateAverage(salaryAndBenefitsSum);
        final double workLifeBalanceAvg = calculateAverage(workLifeBalanceSum);
        final double organizationalCultureAvg = calculateAverage(organizationalCultureSum);
        final double careerAdvancementAvg = calculateAverage(careerAdvancementSum);

        final CompanyGrades updatedGrades = CompanyGrades.ExceptTotalBuilder()
                .salaryAndBenefits(salaryAndBenefitsAvg)
                .workLifeBalance(workLifeBalanceAvg)
                .organizationalCulture(organizationalCultureAvg)
                .careerAdvancement(careerAdvancementAvg)
                .build();

        log.info("totalAvg : {}", updatedGrades.getTotal());
        log.info("salaryAndBenefitsAvg : {}", salaryAndBenefitsAvg);
        log.info("workLifeBalanceAvg : {}", workLifeBalanceAvg);
        log.info("organizationalCultureAvg : {}", organizationalCultureAvg);
        log.info("careerAdvancementAvg : {}", careerAdvancementAvg);
        log.info("-----");

        return updatedGrades;
    }

    private double calculateAverage(final double sum) {
        if(reviewCnt==0) {
            return 0;
        }

        return Math.round(sum/reviewCnt*10)/10.0;
    }

    private void init() {
        reviewPage = 1;
        reviewCnt = 0;
        salaryAndBenefitsSum = workLifeBalanceSum = organizationalCultureSum = careerAdvancementSum = 0.0;
    }

}