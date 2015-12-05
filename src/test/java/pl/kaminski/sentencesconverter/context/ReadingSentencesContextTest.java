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
        readingSentencesContext.setSentenceWordsCount(18);
        Assert.assertSame(18, readingSentencesContext.getMaxWordsInSentence());
    }
}