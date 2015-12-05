package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.List;

/**
 * Create sentence object from string sentence.
 */
@Component
public class SentenceMapper implements LineMapper<Sentence> {

    @Override
    public Sentence mapLine(String line, int lineNumber) throws Exception {
        List<String> wordList = Splitter.on(CharMatcher.anyOf(" ,\t\n\r:!")).trimResults().omitEmptyStrings().splitToList(line);
        Sentence.Builder builder = new Sentence.Builder(lineNumber);
        wordList.stream().forEach(builder::addWord);
        return builder.build();
    }
}
