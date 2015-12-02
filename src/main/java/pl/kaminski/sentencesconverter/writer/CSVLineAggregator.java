package pl.kaminski.sentencesconverter.writer;

import com.google.common.base.Joiner;
import org.springframework.batch.item.file.transform.LineAggregator;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.text.MessageFormat;

/**
 * Created by Paweł Kamiński.
 */
public class CSVLineAggregator implements LineAggregator<Sentence> {

    private static final String SENTENCE = "Sentence {0}, ";

    @Override
    public String aggregate(Sentence item) {
        return MessageFormat.format(SENTENCE, item.getSentenceNumber()) + Joiner.on(", ").join(item.getWords());
    }
}
