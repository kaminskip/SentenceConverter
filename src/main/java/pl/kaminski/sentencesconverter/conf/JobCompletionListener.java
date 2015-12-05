package pl.kaminski.sentencesconverter.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Job listener for informing about completed job
 */
@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job started in: " + jobExecution.getStartTime());
            log.info("Job finished at: " + jobExecution.getEndTime());
            log.info("JOB FINISHED!");
        }
    }
}
