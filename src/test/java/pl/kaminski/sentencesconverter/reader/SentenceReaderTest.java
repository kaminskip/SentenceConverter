package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Sentence reader test
 */
public class SentenceReaderTest {

    private SentenceReader sentenceReader;

    private static final Log logger = LogFactory.getLog(SentenceReaderTest.class);

    private int[] sentencesSizes = new int[]{7, 12, 13, 8, 6, 20, 13, 19, 20, 28, 33, 33};

    @Before
    public void setUp() throws Exception {
        sentenceReader = new SentenceReader();
        sentenceReader.setResource(new ClassPathResource("small.in"));
        sentenceReader.setLineMapper(new SentenceMapper());
        sentenceReader.setEncoding("UTF-8");
        sentenceReader.doOpen();
    }

    @Test
    public void testDoRead() throws Exception {
        List<Sentence> sentences = new ArrayList<>();
        Sentence sentence;
        logger.debug("Run test: " + SentenceReaderTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        while((sentence = sentenceReader.doRead()) != null) {
            sentences.add(sentence);
        }
        timer.stop();
        Assert.assertSame(13, sentences.size());
        for (int i = 0; i < sentencesSizes.length;i++){
            Assert.assertSame("Sentence " + (i + 1) + " do not have " + sentencesSizes[i] + " words.", sentencesSizes[i], sentences.get(i).getNumberOfWords());
        }
        logger.debug("Test: " + SentenceReaderTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }

    @After
    public void tearDown() throws Exception {
        sentenceReader.doClose();
    }
}