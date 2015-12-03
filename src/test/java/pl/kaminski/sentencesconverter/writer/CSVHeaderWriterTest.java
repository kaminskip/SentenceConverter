package pl.kaminski.sentencesconverter.writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class CSVHeaderWriterTest {

    private ReadingSentencesContext context;

    private CSVHeaderWriter csvHeaderWriter;

    @Before
    public void setUp() throws Exception {
        csvHeaderWriter = new CSVHeaderWriter();
        csvHeaderWriter.setResource(new ClassPathResource("CSVHeaderWriterTest.out"));
        context = new ReadingSentencesContext();
        context.setSentenceWordsCount(12);
        csvHeaderWriter.setContext(context);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddHeader() throws Exception {
        csvHeaderWriter.addHeader();
    }
}