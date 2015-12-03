package pl.kaminski.sentencesconverter.writer;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.reader.SentenceMapper;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class CSVLineAggregatorTest {

    private static final Log logger = LogFactory.getLog(CSVLineAggregatorTest.class);

    private CSVLineAggregator lineAggregator;

    @Before
    public void setUp() throws Exception {
        lineAggregator = new CSVLineAggregator();
    }

    @Test
    public void testAggregate() throws Exception {
        logger.debug("Run test: " + CSVLineAggregatorTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        Sentence sentence = new Sentence.Builder(1).addWord("b").addWord("a").addWord("c").build();
        String aggregated = lineAggregator.aggregate(sentence);
        timer.stop();
        Assert.assertEquals("Sentence 1, a, b, c", aggregated);
        logger.debug("Test: " + CSVLineAggregatorTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}