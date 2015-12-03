package pl.kaminski.sentencesconverter.context;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Paweł Kamiński.
 */
public class ReadingSentencesContextTest {

    private ReadingSentencesContext readingSentencesContext = new ReadingSentencesContext();

    @Test
    public void testGetMaxWordsInSentence() throws Exception {
        readingSentencesContext.setSentenceWordsCount(12);
        readingSentencesContext.setSentenceWordsCount(1);
        readingSentencesContext.setSentenceWordsCount(3);
        readingSentencesContext.setSentenceWordsCount(18);
        readingSentencesContext.setSentenceWordsCount(10);
        readingSentencesContext.setSentenceWordsCount(2);
        readingSentencesContext.setSentenceWordsCount(7);
        Assert.assertSame(18, readingSentencesContext.getMaxWordsInSentence());
    }
}