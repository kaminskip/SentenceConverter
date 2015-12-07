package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.TimeUnit;

/**
 * Test reading long test
 */
public class LongSentenceReaderTest {

    private SentenceReader sentenceReader;

    private static final Log logger = LogFactory.getLog(LongSentenceReaderTest.class);

    @Before
    public void setUp() throws Exception {
        sentenceReader = new SentenceReader();
        sentenceReader.setResource(new ClassPathResource("large.in"));
        sentenceReader.setLineMapper(new SentenceMapper());
        sentenceReader.setEncoding("UTF-8");
    }

    @Test
    public void testReadAllSentences() throws Exception {
        logger.debug("Run test: " + LongSentenceReaderTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        int maxWordsInSentence = sentenceReader.getMaxWordsInSentence();
        Assert.assertEquals(25, maxWordsInSentence);
        timer.stop();
        logger.debug("Test: " + LongSentenceReaderTest.class + " succeed in " + timer.elapsed(TimeUnit.SECONDS) + " seconds.");
    }
}