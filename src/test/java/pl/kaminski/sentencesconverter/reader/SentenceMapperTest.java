package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceMapperTest {

    private static final Log logger = LogFactory.getLog(SentenceMapperTest.class);

    private String sampleText;

    private String[] expectedStrings;

    @Before
    public void setUp() throws Exception {
        sampleText = "What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese \n isn't my mother tongue";
        expectedStrings = new String[]{"What", "he", "shouted", "was", "shocking", "停在那儿", "你这肮脏的掠夺者", "I", "couldn't", "understand", "a", "word", "perhaps", "because", "Chinese", "isn't", "my", "mother", "tongue"};
    }

    @Test
    public void testMapLine() throws Exception {
        logger.debug("Run test: " + SentenceMapperTest.class);
        SentenceMapper sentenceMapper = new SentenceMapper();
        Stopwatch timer = Stopwatch.createStarted();
        String[] strings = sentenceMapper.mapLine(sampleText, 1).toArray(new String[]{});
        timer.stop();
        Assert.assertArrayEquals(expectedStrings, strings);
        logger.debug("Test: " + SentenceMapperTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}