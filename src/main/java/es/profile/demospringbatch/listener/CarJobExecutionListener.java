package es.profile.demospringbatch.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class CarJobExecutionListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Executing cars job with id {}", jobExecution.getId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Cars job with id {} execution completed", jobExecution.getId());
        }
    }
}
