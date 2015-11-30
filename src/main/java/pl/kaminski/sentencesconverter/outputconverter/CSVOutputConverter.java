package pl.kaminski.sentencesconverter.outputconverter;

import com.google.common.base.Joiner;
import org.apache.log4j.Logger;
import pl.kaminski.sentencesconverter.OutputType;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by Paweł Kamiński.
 */
public class CSVOutputConverter implements OutputConverter {

    final static Logger log = Logger.getLogger(CSVOutputConverter.class);

    public CSVOutputConverter() {
        log.info(CSVOutputConverter.class + " initialized.");
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.CSV;
    }

    @Override
    public void write(OutputStream outputStream, Sentence sentence) {
        Joiner joiner = Joiner.on(", ").skipNulls();
        final String join = joiner.join(sentence.getWords());
        log.info("join: " + join.toString());
        //Writer writer = new Wri
    }
}
