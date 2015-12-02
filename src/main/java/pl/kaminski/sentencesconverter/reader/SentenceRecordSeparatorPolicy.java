package pl.kaminski.sentencesconverter.reader;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;

import java.util.Iterator;

/**
 * Created by Paweł Kamiński.
 */
public class SentenceRecordSeparatorPolicy extends DefaultRecordSeparatorPolicy {

    private static final String DELIMITER = ".";

    private String sentence = "";

    @Override
    public boolean isEndOfRecord(String record) {
        Preconditions.checkNotNull(record);
        return record.contains(DELIMITER);
    }

    @Override
    public String postProcess(String record) {
        Preconditions.checkNotNull(record);
        if(record.contains(DELIMITER)){
            Iterator<String> iterator = Splitter.on(DELIMITER).split(record).iterator();
            return iterator.next();
        }
        return record;
    }

    @Override
    public String preProcess(String record) {
        Preconditions.checkNotNull(record);
        if(record.contains(DELIMITER)){
            Iterator<String> iterator = Splitter.on(DELIMITER).split(record).iterator();
            sentence += iterator.next();
            return iterator.next();
        }
        return record;
    }
}
