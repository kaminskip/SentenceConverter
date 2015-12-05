package pl.kaminski.sentencesconverter.writer.csv;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.test.AssertFile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

/**
 * Created by Paweł Kamiński.
 */
public class CSVHeaderWriterTest {

    private static final Log logger = LogFactory.getLog(CSVHeaderWriterTest.class);

    private CSVHeaderWriter csvHeaderWriter;

    private Resource resource = new ClassPathResource("CSVHeaderWriterTest.out");

    private Resource originalResource = new ClassPathResource("CSVHeaderWriterTest.original.out");

    @Before
    public void setUp() throws Exception {
        //Create temp files
        File tempFile =  Files.createTempFile("CSVHeaderWriterTest", ".tmp").toFile();
        tempFile.deleteOnExit();
        Resource tempResource;
        tempResource = new FileSystemResource(tempFile);

        //Copy to temp files
        Files.copy(resource.getFile().toPath(), tempResource.getFile().toPath(), StandardCopyOption.REPLACE_EXISTING);

        csvHeaderWriter = new CSVHeaderWriter();

        ReadingSentencesContext context;
        csvHeaderWriter.setResource(tempResource);
        context = new ReadingSentencesContext();
        context.setSentenceWordsCount(12);
        csvHeaderWriter.setContext(context);
    }

    @Test
    public void testAddHeader() throws Exception {
        logger.debug("Run test: " + CSVHeaderWriterTest.class);
        Stopwatch timer = Stopwatch.createStarted();
        csvHeaderWriter.addHeader();
        logger.info("Compare files");
        logger.info(originalResource.getFile().getAbsoluteFile());
        logger.info(resource.getFile().getAbsoluteFile());
        AssertFile.assertFileEquals(originalResource, resource);
        logger.debug("Test: " + CSVHeaderWriterTest.class + " succeed in " + timer.elapsed(TimeUnit.MILLISECONDS) + " milliseconds.");
    }
}