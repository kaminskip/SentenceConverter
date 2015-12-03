package pl.kaminski.sentencesconverter.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceTest {

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
    }

    @Test
    public void testGetNumberOfWords() throws Exception {
        Assert.assertSame(5, firstSentence.getNumberOfWords());
        Assert.assertSame(8, secondSentence.getNumberOfWords());
    }
}