package com.stubee.companyaveragebatch.processor;

import com.stubee.applicationcommons.dtos.request.PageRequest;
import com.stubee.batchcommons.annotations.Processor;
import com.stubee.reviewapplication.services.query.response.ReviewInfoResponse;
import com.stubee.reviewapplication.usecases.query.QueryReviewInfoListByCompanyUseCase;
import com.stubee.rollingdomains.common.model.Grades;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

@Processor
@Slf4j
@RequiredArgsConstructor
public class CompanyGradesAvgProcessor implements ItemProcessor<List<Company>, List<Company>> {

    private static final long PAGE_SIZE = 100;

    private final QueryReviewInfoListByCompanyUseCase queryReviewInfoListByCompanyUseCase;

    private double salaryAndBenefitsSum;
    private double workLifeBalanceSum;
    private double organizationalCultureSum;
    private double careerAdvancementSum;

    @Override
    public List<Company> process(final List<Company> readCompanyList) {
        log.info("<<<<<Processor Process>>>>>");
        log.info("ReadCompanyList Size : {}", readCompanyList.size());

        final List<Company> processedCompanyList = new ArrayList<>();

        for (Company company : readCompanyList) {
            log.info("Company Name : {}", company.companyDetails().name());

            long reviewPage = 1, reviewCnt = 0;
            this.initSums();

            while (true) {
                final List<ReviewInfoResponse> reviewList = queryReviewInfoListByCompanyUseCase.get(
                        company.companyId().getId(), pageRequest(reviewPage)).data();

                calculateSum(reviewList);

                reviewCnt += reviewList.size();
                reviewPage++;

                if (reviewList.size() < PAGE_SIZE) {
                    break;
                }
            }

            final double salaryAndBenefitsAvg = calculateAvg(salaryAndBenefitsSum, reviewCnt);
            final double workLifeBalanceAvg = calculateAvg(workLifeBalanceSum, reviewCnt);
            final double organizationalCultureAvg = calculateAvg(organizationalCultureSum, reviewCnt);
            final double careerAdvancementAvg = calculateAvg(careerAdvancementSum, reviewCnt);

            Grades updatedGrades = Grades.create(salaryAndBenefitsAvg, workLifeBalanceAvg,
                    organizationalCultureAvg, careerAdvancementAvg);

            Company updatedCompany = company.updateGrades(updatedGrades);

            log.info("totalAvg : {}", updatedGrades.totalGrade());
            log.info("salaryAndBenefitsAvg : {}", salaryAndBenefitsAvg);
            log.info("workLifeBalanceAvg : {}", workLifeBalanceAvg);
            log.info("organizationalCultureAvg : {}", organizationalCultureAvg);
            log.info("careerAdvancementAvg : {}", careerAdvancementAvg);

            processedCompanyList.add(updatedCompany);
        }

        log.info("<<<<<Processor End>>>>>");

        return processedCompanyList;
    }

    private void initSums() {
        salaryAndBenefitsSum = workLifeBalanceSum = organizationalCultureSum = careerAdvancementSum = 0.0;
    }

    private PageRequest pageRequest(final long page) {
        return new PageRequest(page, PAGE_SIZE);
    }

    private void calculateSum(final List<ReviewInfoResponse> reviewList) {
        for (ReviewInfoResponse response : reviewList) {
            salaryAndBenefitsSum += response.salaryAndBenefits();
            workLifeBalanceSum += response.workLifeBalance();
            organizationalCultureSum += response.organizationalCulture();
            careerAdvancementSum += response.careerAdvancement();
        }
    }

    private double calculateAvg(final double sum, final long reviewCnt) {
        return reviewCnt==0 ? 0 : Math.round(sum/reviewCnt*10)/10.0;
    }

}