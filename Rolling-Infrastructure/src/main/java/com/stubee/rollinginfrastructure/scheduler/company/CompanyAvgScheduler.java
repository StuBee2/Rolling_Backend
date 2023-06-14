package com.stubee.rollinginfrastructure.scheduler.company;

import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingcore.common.dto.PageRequest;
import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CompanyAvgScheduler {

    private static long companyPage = 1;
    private static final long SIZE = 10;

    private final QueryCompanyUseCase queryCompanyUseCase;
    private final CommandCompanyUseCase commandCompanyUseCase;

    private final QueryReviewUseCase queryReviewUseCase;

    //리펙 필요
    /** 매일 3 am */
    @Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Seoul")
    public void calculateAverage() {
        while(true) {
            List<Company> companyList = queryCompanyUseCase.getList(pageRequest(companyPage));

            /** Company List가 비었음 */
            if(companyList.isEmpty()) {
                log.info("<<<Company List is Empty>>>");
                break;
            }

            /** 각 Company별 Grades 통계 */
            for (Company company : companyList) {
                log.info("<<<<<CompanyId : " + company.companyId().id() + ", START" + " >>>>>");
                long reviewPage = 1, reviewCnt = 0;
                double totalSum = 0, balanceSum = 0, salarySum = 0, welfareSum = 0;

                /** Review Paging */
                while(true) {
                    List<ReviewInfoResponse> reviewList = queryReviewUseCase.getByCompanyId(company.companyId().id(),
                                    new PageRequest(reviewPage, SIZE)).data();

                    for(ReviewInfoResponse response : reviewList) {
                        totalSum += response.totalGrade();
                        balanceSum += response.balanceGrade();
                        salarySum += response.salaryGrade();
                        welfareSum += response.welfareGrade();
                    }

                    reviewCnt += reviewList.size();

                    reviewPage++;
                    if(reviewList.size()<SIZE) {
                        break;
                    }
                }

                log.info("==> reviewCnt : " + reviewCnt);
                final double totalAvg = totalSum/reviewCnt;
                final double balanceAvg = balanceSum/reviewCnt;
                final double salaryAvg = salarySum/reviewCnt;
                final double welfareAvg = welfareSum/reviewCnt;
                log.info("==> totalAvg : " + totalAvg);
                log.info("==> balanceAvg : " + balanceAvg);
                log.info("==> salaryAvg : " + salaryAvg);
                log.info("==> welfareAvg : " + welfareAvg);

                if(reviewCnt==0) {
                    continue;
                }

                Company updatedCompany = company.updateGrades(totalAvg, balanceAvg, salaryAvg, welfareAvg);
                log.debug("==> CompanyId : " + updatedCompany.companyId().id());

                commandCompanyUseCase.update(updatedCompany);
            }

            if(companyList.size()<SIZE) {
                log.info("<<<Company Paging End>>>");
                break;
            }

            companyPage++;
        }
    }

    private PageRequest pageRequest(final long page) {
        return new PageRequest(page, CompanyAvgScheduler.SIZE);
    }

}