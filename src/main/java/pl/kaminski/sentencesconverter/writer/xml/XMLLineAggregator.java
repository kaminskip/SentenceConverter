package pl.kaminski.sentencesconverter.writer.xml;

import com.google.common.xml.XmlEscapers;
import org.springframework.batch.item.file.transform.LineAggregator;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.model.Word;

import java.text.MessageFormat;
import java.util.stream.Collectors;

/**
 * Aggregate Sentence object to sentence tag
 */
public class XMLLineAggregator implements LineAggregator<Sentence> {

    //Sentence tag format
    private static final String SENTENCE_TAG = "<sentence>{0}</sentence>";

    //Word tag format
    private static final String WORD_TAG = "<word>{0}</word>";

    @Override
    public String aggregate(Sentence item) {
        String words = item.getWords().stream().map(XMLLineAggregator::createWordTag).collect(Collectors.joining());
        return MessageFormat.format(SENTENCE_TAG, words);
    }

    /**
     * Create xml word tag with escaped string
     * @param word plain string
     * @return escaped string with tag
     */
    private static String createWordTag(Word word){
        String wordString = XmlEscapers.xmlAttributeEscaper().escape(word.getWord());
        return MessageFormat.format(WORD_TAG, wordString);
    }
}
