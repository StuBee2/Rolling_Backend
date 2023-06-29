package com.stubee.rollingbatch.company.job.config;

import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingapplication.domain.company.port.api.QueryCompanyUseCase;
import com.stubee.rollingapplication.domain.review.port.api.QueryReviewUseCase;
import com.stubee.rollingbatch.company.processor.CompanyGradesAvgProcessor;
import com.stubee.rollingbatch.company.reader.CompanyListReader;
import com.stubee.rollingbatch.company.writer.CompanyListWriter;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CalculateCompanyGradesAvgJobConfig {

    private static final int CHUNK_SIZE = 1;

    private final QueryCompanyUseCase queryCompanyUseCase;
    private final CommandCompanyUseCase commandCompanyUseCase;
    private final QueryReviewUseCase queryReviewUseCase;

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
                .reader(companyReader())
                .processor(companyProcessor())
                .writer(companyWriter())
                .build();
    }

    @Bean
    public ItemReader<List<Company>> companyReader() {
        return new CompanyListReader(queryCompanyUseCase);
    }

    @Bean
    public ItemProcessor<List<Company>, List<Company>> companyProcessor() {
        return new CompanyGradesAvgProcessor(queryReviewUseCase);
    }

    @Bean
    public ItemWriter<List<Company>> companyWriter() {
        return new CompanyListWriter(commandCompanyUseCase);
    }

}