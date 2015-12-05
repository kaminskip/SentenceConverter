package pl.kaminski.sentencesconverter.writer.csv;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;
import pl.kaminski.sentencesconverter.reader.SentenceReader;

import java.util.concurrent.TimeUnit;

/**
 * Listener for adding header to csv file
 */
@Component
public class AddCSVHeaderToFileListener extends JobExecutionListenerSupport {

    private static final Log logger = LogFactory.getLog(AddCSVHeaderToFileListener.class);

    @Autowired
    private SentenceReader sentenceReader;

    @Autowired
    private ReadingSentencesContext context;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Read input file.");
        Stopwatch timer = Stopwatch.createStarted();
        int max = sentenceReader.getMaxWordsInSentence();
        context.setSentenceWordsCount(max);
        timer.stop();
        logger.info("File read in " + timer.elapsed(TimeUnit.SECONDS) + " seconds.");
        logger.info("Max words in sentences: " + max);
    }

    public void setSentenceReader(SentenceReader sentenceReader) {
        this.sentenceReader = sentenceReader;
    }

    public void setContext(ReadingSentencesContext context) {
        this.context = context;
    }

}
