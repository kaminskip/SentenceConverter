package pl.kaminski.sentencesconverter.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class WordTest {

    @Test
    public void testCompareTo() throws Exception {
        Word word1 = new Word.Builder().word("abc").build();
        Word word2 = new Word.Builder().word("aac").build();
        Word word3 = new Word.Builder().word("bcc").build();
        Word word3same = new Word.Builder().word("bcc").build();

        Assert.assertSame(1, word1.compareTo(word2));
        Assert.assertSame(-1, word1.compareTo(word3));

        Assert.assertSame(-1, word2.compareTo(word1));
        Assert.assertSame(-1, word2.compareTo(word3));

        Assert.assertSame(1, word3.compareTo(word1));
        Assert.assertSame(1, word3.compareTo(word2));
        Assert.assertSame(0, word3.compareTo(word3same));
    }

    @Test
    public void testGetWord() throws Exception {
        Word word = new Word.Builder().word("word").build();
        Assert.assertSame("word", word.getWord());
    }

    @Test(expected = NullPointerException.class)
    public void testPrecondition() throws Exception {
        new Word.Builder().build();
    }
}