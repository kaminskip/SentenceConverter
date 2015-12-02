package pl.kaminski.sentencesconverter.writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class CSVItemWriterTest {

    private static final Log logger = LogFactory.getLog(CSVItemWriterTest.class);

    private CSVItemWriter csvItemWriter;

    @Before
    public void setUp() throws Exception {
        csvItemWriter = new CSVItemWriter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWrite() throws Exception {
        List<Sentence> sentences = new ArrayList<>();
        csvItemWriter.open(new ExecutionContext());
        csvItemWriter.write(sentences);
        csvItemWriter.close();
    }
}