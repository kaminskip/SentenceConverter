package pl.kaminski.sentencesconverter.outputconverter;

import pl.kaminski.sentencesconverter.OutputType;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.io.OutputStream;

/**
 * Created by Paweł Kamiński.
 */
public interface OutputConverter {

    OutputType getOutputType();

    void write(OutputStream outputStream, Sentence sentence);
}
