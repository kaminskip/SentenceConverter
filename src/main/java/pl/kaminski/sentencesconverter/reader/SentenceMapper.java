package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.file.LineMapper;

import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceMapper implements LineMapper<List<String>> {

    private static final Log logger = LogFactory.getLog(SentenceMapper.class);

    @Override
    public List<String> mapLine(String line, int lineNumber) throws Exception {
        logger.debug("Split line " + lineNumber +": " + line);
        return Splitter.on(CharMatcher.anyOf(" ,\t\n\r:!")).trimResults().omitEmptyStrings().splitToList(line);
    }
}
