package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import javafx.collections.transformation.SortedList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class SentenceMapper implements LineMapper<Sentence> {

    @Override
    public Sentence mapLine(String line, int lineNumber) throws Exception {
        List<String> wordList = Splitter.on(CharMatcher.anyOf(" ,\t\n\r:!")).trimResults().omitEmptyStrings().splitToList(line);
        Sentence.Builder builder = new Sentence.Builder(lineNumber);
        for (String word: wordList){
            builder.addWord(word);
        }
        return builder.build();
    }
}
