package rolling.rollingbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@Slf4j
@EnableScheduling
@RequiredArgsConstructor
public class CompanyGradesAvgScheduler {

    private final JobLauncher jobLauncher;
    private final Job calAvgJob;

    //@Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Seoul")
    @Scheduled(cron = "10 * * * * *", zone = "Asia/Seoul")
    public void calculateAvg() throws Exception {
        final JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        log.info("Calculate Avg Batch Start : {}", LocalDateTime.now());

        jobLauncher.run(calAvgJob, jobParameters);
    }

}