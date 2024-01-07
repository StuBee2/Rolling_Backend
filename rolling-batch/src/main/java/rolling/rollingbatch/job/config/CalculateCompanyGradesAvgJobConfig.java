package rolling.rollingbatch.job.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import rolling.domain.company.model.Company;
import rolling.rollingbatch.processor.CompanyGradesAvgProcessor;
import rolling.rollingbatch.reader.CompanyListReader;
import rolling.rollingbatch.writer.CompanyListWriter;

import java.util.List;

import static rolling.rollingbatch.job.consts.BatchConstants.CHUNK_SIZE;

@Configuration
@RequiredArgsConstructor
public class CalculateCompanyGradesAvgJobConfig {

    private final CompanyListReader companyListReader;
    private final CompanyGradesAvgProcessor companyGradesAvgProcessor;
    private final CompanyListWriter companyListWriter;

    @Bean
    public Job calculateAverageJob(final Step calAvgStep, final JobRepository jobRepository) {
        return new JobBuilder("calAvgJob", jobRepository)
                .flow(calAvgStep)
                .end()
                .build();
    }

    @Bean
    public Step calculateAverageStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder("calAvgStep", jobRepository)
                .<List<Company>, List<Company>>chunk(CHUNK_SIZE, transactionManager)
                .reader(companyListReader)
                .processor(companyGradesAvgProcessor)
                .writer(companyListWriter)
                .build();
    }

}