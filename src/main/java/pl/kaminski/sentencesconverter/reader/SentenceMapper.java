package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class SentenceMapper implements LineMapper<List<String>> {

    @Override
    public List<String> mapLine(String line, int lineNumber) throws Exception {
        return Splitter.on(CharMatcher.anyOf(" ,\t\n\r:!")).trimResults().omitEmptyStrings().splitToList(line);
    }
}
