package pl.kaminski.sentencesconverter.writer.csv;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.LineAggregator;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.text.MessageFormat;

/**
 * CSV line aggregator, create comma separated words from sentence object
 */
public class CSVLineAggregator implements LineAggregator<Sentence> {

    private static final String SENTENCE = "Sentence {0}, ";

    /**
     * Aggregate words from sentence
     * @param sentence sentence object
     * @return string with words
     */
    @Override
    public String aggregate(Sentence sentence) {
        return MessageFormat.format(SENTENCE, sentence.getSentenceNumber()) + Joiner.on(", ").join(sentence.getSortedWords());
    }
}
