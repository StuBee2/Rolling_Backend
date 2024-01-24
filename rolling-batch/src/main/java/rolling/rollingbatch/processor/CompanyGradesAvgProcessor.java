package rolling.rollingbatch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import rolling.application.story.interactor.query.StoryQueryByCompanyResponse;
import rolling.application.story.outport.QueryStoryPort;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyGrades;

import java.util.ArrayList;
import java.util.List;

import static rolling.rollingbatch.job.consts.BatchConstants.PAGE_SIZE;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyGradesAvgProcessor implements ItemProcessor<List<Company>, List<Company>> {

    private final QueryStoryPort queryStoryPort;

    private double salaryAndBenefitsSum;
    private double workLifeBalanceSum;
    private double organizationalCultureSum;
    private double careerAdvancementSum;
    private long reviewPage;
    private long reviewCnt;

    @BeforeProcess
    public void beforeProcess() {
        log.info("-----Processor Start----");
    }

    @AfterProcess
    public void afterProcess() {
        log.info("-----Processor End-----");
    }

    @Override
    public List<Company> process(final List<Company> readCompanyList) {
        final List<Company> processedCompanyList = new ArrayList<>();

        readCompanyList.forEach(company -> {
            log.info("Company Name : {}", company.details().name());

            this.init();
            this.calculateSums(company.id().getId());

            company.modify(this.calculateAverages());

            processedCompanyList.add(company);
        });

        return processedCompanyList;
    }

    private void calculateSums(final Long companyId) {
        while (true) {
            final List<StoryQueryByCompanyResponse> reviewList = queryStoryPort.findByCompany(companyId, PageRequest.of(reviewPage, PAGE_SIZE));

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