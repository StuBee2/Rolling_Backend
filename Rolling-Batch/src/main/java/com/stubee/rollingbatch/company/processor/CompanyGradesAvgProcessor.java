package com.stubee.rollingbatch.company.processor;

import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CompanyGradesAvgProcessor implements ItemProcessor<List<Company>, List<Company>> {

    private static final long PAGE_SIZE = 100;

    private final QueryReviewUseCase queryReviewUseCase;

    private double totalSum;
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
            long reviewPage = 1, reviewCnt = 0;
            totalSum = salaryAndBenefitsSum = workLifeBalanceSum = organizationalCultureSum = careerAdvancementSum = 0.0;

            while (true) {
                final List<ReviewInfoResponse> reviewList = queryReviewUseCase.getByCompanyId(
                        company.companyId().id(), pageRequest(reviewPage)).data();

                calculateSum(reviewList);

                reviewCnt += reviewList.size();
                reviewPage++;

                if (reviewList.size() < PAGE_SIZE) {
                    break;
                }
            }

            final double totalAvg = calculateAvg(totalSum, reviewCnt);
            final double salaryAndBenefitsAvg = calculateAvg(salaryAndBenefitsSum, reviewCnt);
            final double workLifeBalanceAvg = calculateAvg(workLifeBalanceSum, reviewCnt);
            final double organizationalCultureAvg = calculateAvg(organizationalCultureSum, reviewCnt);
            final double careerAdvancementAvg = calculateAvg(careerAdvancementSum, reviewCnt);

            Company updatedCompany = company.updateGrades(totalAvg, salaryAndBenefitsAvg, workLifeBalanceAvg,
                    organizationalCultureAvg, careerAdvancementAvg);

            log.info("totalAvg : {}", totalAvg);
            log.info("salaryAndBenefitsAvg : {}", salaryAndBenefitsAvg);
            log.info("workLifeBalanceAvg : {}", workLifeBalanceAvg);
            log.info("organizationalCultureAvg : {}", organizationalCultureAvg);
            log.info("careerAdvancementAvg : {}", careerAdvancementAvg);
            log.info("Company Name : {}", updatedCompany.companyDetails().name());

            processedCompanyList.add(updatedCompany);
        }

        log.info("<<<<<Processor End>>>>>");

        return processedCompanyList;
    }

    private PageRequest pageRequest(final long page) {
        return new PageRequest(page, PAGE_SIZE);
    }

    private void calculateSum(final List<ReviewInfoResponse> reviewList) {
        for (ReviewInfoResponse response : reviewList) {
            totalSum += response.totalGrade();
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