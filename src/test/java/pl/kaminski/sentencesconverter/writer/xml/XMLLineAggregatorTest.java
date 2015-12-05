package pl.kaminski.sentencesconverter.writer.xml;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.writer.csv.CSVLineAggregator;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class XMLLineAggregatorTest {

    private static final Log logger = LogFactory.getLog(XMLLineAggregatorTest.class);

    private XMLLineAggregator lineAggregator;

    @Before
    public void setUp() throws Exception {
        lineAggregator = new XMLLineAggregator();
    }

    @Test
    public void testAggregate() throws Exception {
        logger.debug("Run test: " + XMLLineAggregatorTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        Sentence sentence = new Sentence.Builder(1).addWord("b").addWord("a").addWord("c").build();
        String aggregated = lineAggregator.aggregate(sentence);
        timer.stop();
        Assert.assertEquals("<sentence><word>b</word><word>a</word><word>c</word></sentence>", aggregated);
        logger.debug("Test: " + XMLLineAggregatorTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}