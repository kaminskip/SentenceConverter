package pl.kaminski.sentencesconverter.impl;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.junit.Test;
import pl.kaminski.sentencesconverter.OutputType;
import pl.kaminski.sentencesconverter.SentencesConverter;

import java.io.InputStream;

/**
 * Created by Paweł Kamiński.
 */
public class SentencesConverterImplTest {

    final static Logger log = Logger.getLogger(SentencesConverterImplTest.class);

    public static final String SAMPLE_TEXT = " Marry  had a little lamb .\n\n\n" +
            "Peter  called for the wolf  , and Aesop came.\n" +
            "Cinderella likes shoes..";

    @Test
    public void testProcessCsv() throws Exception {
        log.debug("Start test");
        SentencesConverter sentencesConverter = new SentencesConverterImpl();
        InputStream inputStream = IOUtils.toInputStream(SAMPLE_TEXT, "UTF-8");
        sentencesConverter.setInputStream(inputStream);
        ByteArrayOutputStream csvOutputStream = new ByteArrayOutputStream();
        sentencesConverter.addOutputStream(csvOutputStream, OutputType.CSV);
        sentencesConverter.process();
        log.debug("output: " + csvOutputStream.toString("UTF-8"));
        log.debug("End test");
    }
}