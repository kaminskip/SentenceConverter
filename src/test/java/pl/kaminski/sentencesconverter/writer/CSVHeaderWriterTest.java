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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class CSVHeaderWriterTest {

    private static final Log logger = LogFactory.getLog(CSVHeaderWriterTest.class);

    private ReadingSentencesContext context;

    private CSVHeaderWriter csvHeaderWriter;

    private Resource resource = new ClassPathResource("CSVHeaderWriterTest.out");

    private Resource tempResource;

    private Resource originalResource = new ClassPathResource("CSVHeaderWriterTest.original.out");

    @Before
    public void setUp() throws Exception {
        //Create temp files
        File tempFile =  Files.createTempFile("CSVHeaderWriterTest", ".tmp").toFile();
        tempFile.deleteOnExit();
        tempResource = new FileSystemResource(tempFile);

        //Copy to temp files
        Files.copy(resource.getFile().toPath(), tempResource.getFile().toPath(), StandardCopyOption.REPLACE_EXISTING);

        csvHeaderWriter = new CSVHeaderWriter();

        csvHeaderWriter.setResource(tempResource);
        context = new ReadingSentencesContext();
        context.setSentenceWordsCount(12);
        csvHeaderWriter.setContext(context);
    }

    @Test
    public void testAddHeader() throws Exception {
        logger.debug("Run test: " + CSVHeaderWriterTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        logger.debug("Before write to file: " + resource.contentLength() + " B.");
        csvHeaderWriter.addHeader();
        Assert.assertEquals(originalResource.contentLength(), tempResource.contentLength());
        logger.debug("Write to file: " + resource.contentLength() + " B.");
        logger.debug("Test: " + CSVHeaderWriterTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}