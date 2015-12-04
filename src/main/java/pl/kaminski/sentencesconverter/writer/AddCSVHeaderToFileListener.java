package pl.kaminski.sentencesconverter.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Listener for adding header to csv file
 */
@Component
public class AddCSVHeaderToFileListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(AddCSVHeaderToFileListener.class);

    @Autowired
    private CSVHeaderWriter csvHeaderWriter;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Append at the beginning of file header.");
            try {
                csvHeaderWriter.addHeader();
                log.info("Header added.");
            } catch (IOException e) {
                log.error("Cannot append header", e);
            }
        }
    }

    public void setCsvHeaderWriter(CSVHeaderWriter csvHeaderWriter) {
        this.csvHeaderWriter = csvHeaderWriter;
    }
}
