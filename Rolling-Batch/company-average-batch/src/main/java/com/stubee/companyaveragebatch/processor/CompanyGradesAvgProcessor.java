package com.stubee.companyaveragebatch.processor;

import com.stubee.rollingdomains.common.dtos.request.PageRequest;
import com.stubee.batchcommons.annotations.Processor;
import com.stubee.reviewapplication.usecases.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoListByCompanyUseCase;
import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.stubee.companyaveragebatch.job.consts.BatchConstants.PAGE_SIZE;

@Processor
@Slf4j
@RequiredArgsConstructor
public class CompanyGradesAvgProcessor implements ItemProcessor<List<Company>, List<Company>> {

    private final QueryReviewInfoListByCompanyUseCase queryReviewInfoListByCompanyUseCase;

    private double salaryAndBenefitsSum;
    private double workLifeBalanceSum;
    private double organizationalCultureSum;
    private double careerAdvancementSum;
    private long reviewPage;
    private long reviewCnt;

    @Override
    public List<Company> process(final List<Company> readCompanyList) {
        log.info("Processor Start");
        log.info("ReadCompanyList Size : {}", readCompanyList.size());

        final List<Company> processedCompanyList = new ArrayList<>();

        readCompanyList.forEach(company -> {
            this.init();

            log.info("Company Name : {}", company.companyDetails().name());

            this.calculateSums(company.companyId().getId());

            final Grades updatedGrades = this.calculateAverages();

            final Company updatedCompany = company.updateGrades(updatedGrades);

            processedCompanyList.add(updatedCompany);
        });

        log.info("Processor End");

        return processedCompanyList;
    }

    private void calculateSums(final UUID companyId) {
        while (true) {
            final List<ReviewInfoResponse> reviewList = queryReviewInfoListByCompanyUseCase.get(companyId,
                    PageRequest.of(reviewPage, PAGE_SIZE)).data();

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

    private Grades calculateAverages() {
        final double salaryAndBenefitsAvg = calculateAverage(salaryAndBenefitsSum);
        final double workLifeBalanceAvg = calculateAverage(workLifeBalanceSum);
        final double organizationalCultureAvg = calculateAverage(organizationalCultureSum);
        final double careerAdvancementAvg = calculateAverage(careerAdvancementSum);

        final Grades updatedGrades = Grades.create(salaryAndBenefitsAvg, workLifeBalanceAvg,
                organizationalCultureAvg, careerAdvancementAvg);

        log.info("totalAvg : {}", updatedGrades.totalGrade());
        log.info("salaryAndBenefitsAvg : {}", salaryAndBenefitsAvg);
        log.info("workLifeBalanceAvg : {}", workLifeBalanceAvg);
        log.info("organizationalCultureAvg : {}", organizationalCultureAvg);
        log.info("careerAdvancementAvg : {}", careerAdvancementAvg);

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