package pl.kaminski.sentencesconverter.writer;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class CSVItemWriterTest {

    private static final Log logger = LogFactory.getLog(CSVItemWriterTest.class);

    private CSVItemWriter csvItemWriter;

    private Resource resource;

    private Resource originalResource;

    private Sentence firstSentence;

    private Sentence secondSentence;

    @Before
    public void setUp() throws Exception {
        firstSentence = new Sentence.Builder(1)
                .addWord("Marry").addWord("had").addWord("a").addWord("little")
                .addWord("lamb").build();

        secondSentence = new Sentence.Builder(2)
                .addWord("Peter").addWord("called").addWord("for").addWord("the")
                .addWord("wolf").addWord("and").addWord("Aesop").addWord("came").build();

        csvItemWriter = new CSVItemWriter();
        resource = new ClassPathResource("CSVItemWriterTest.out");
        originalResource = new ClassPathResource("CSVItemWriterTest.original.out");
        csvItemWriter.setResource(resource);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWrite() throws Exception {
        logger.debug("Run test: " + CSVItemWriterTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        List<Sentence> sentences = new ArrayList<>();
        csvItemWriter.open(new ExecutionContext());
        csvItemWriter.write(sentences);
        csvItemWriter.write(Stream.of(firstSentence, secondSentence).collect(Collectors.toList()));
        csvItemWriter.close();
        Assert.assertSame(originalResource.contentLength(), resource.contentLength());
        logger.debug("Write to file: " + resource.contentLength() + " B.");
        logger.debug("Test: " + CSVItemWriterTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}