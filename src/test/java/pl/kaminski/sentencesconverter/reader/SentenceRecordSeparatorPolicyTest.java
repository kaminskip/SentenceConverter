package pl.kaminski.sentencesconverter.reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceRecordSeparatorPolicyTest {

    private String line1 = "test text without dot";

    private String line2 = "test text with.inside";

    private SentenceRecordSeparatorPolicy  sentenceRecordSeparatorPolicy = new SentenceRecordSeparatorPolicy();

    @Test
    public void testIsEndOfRecord() throws Exception {
        Assert.assertFalse(sentenceRecordSeparatorPolicy.isEndOfRecord(line1));
        Assert.assertTrue(sentenceRecordSeparatorPolicy.isEndOfRecord(line2));
    }

    @Test
    public void testPostProcess() throws Exception {
        Assert.assertSame(line1, sentenceRecordSeparatorPolicy.postProcess(line1));
        Assert.assertEquals("test text with", sentenceRecordSeparatorPolicy.postProcess(line2));
    }

    @Test
    public void testPreProcess() throws Exception {
        Assert.assertSame(line1, sentenceRecordSeparatorPolicy.preProcess(line1));
        Assert.assertEquals("inside", sentenceRecordSeparatorPolicy.preProcess(line2));
    }
}