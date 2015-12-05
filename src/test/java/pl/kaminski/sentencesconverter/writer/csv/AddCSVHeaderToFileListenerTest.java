package pl.kaminski.sentencesconverter.writer.csv;

import org.junit.Test;
import org.springframework.batch.core.JobExecution;

import static org.junit.Assert.*;

/**
 * Test class for AddCSVHeaderToFileListener
 */
public class AddCSVHeaderToFileListenerTest {

    @Test
    public void testAfterJob() throws Exception {
        AddCSVHeaderToFileListener listener = new AddCSVHeaderToFileListener();
        listener.setCsvHeaderWriter(new CSVHeaderWriter());
        listener.afterJob(new JobExecution(new Long(1)));
    }
}