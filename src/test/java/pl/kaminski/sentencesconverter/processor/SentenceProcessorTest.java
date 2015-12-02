package pl.kaminski.sentencesconverter.processor;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.model.Word;
import pl.kaminski.sentencesconverter.reader.SentenceMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceProcessorTest {

    private static final Log logger = LogFactory.getLog(SentenceProcessorTest.class);

    private SentenceProcessor sentenceProcessor;

    private List<String> sentence = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        sentenceProcessor = new SentenceProcessor();
        sentence.add("Marry");
        sentence.add("had");
        sentence.add("a");
        sentence.add("little");
        sentence.add("lamb");
    }

    @Test
    public void testProcess() throws Exception {
        logger.debug("Run test: " + SentenceProcessorTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        Sentence sentence = sentenceProcessor.process(this.sentence);
        List<Word> wordList = sentence.getWords();
        timer.stop();
        Assert.assertSame(5, wordList.size());
        Assert.assertEquals("a", wordList.get(0).getWord());
        Assert.assertEquals("had", wordList.get(1).getWord());
        Assert.assertEquals("lamb", wordList.get(2).getWord());
        Assert.assertEquals("little", wordList.get(3).getWord());
        Assert.assertEquals("Marry", wordList.get(4).getWord());
        logger.debug("Test: " + SentenceProcessorTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}