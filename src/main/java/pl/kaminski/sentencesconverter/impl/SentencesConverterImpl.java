package pl.kaminski.sentencesconverter.impl;

import com.google.common.base.Preconditions;
import pl.kaminski.sentencesconverter.OutputType;
import pl.kaminski.sentencesconverter.SentencesConverter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paweł Kamiński.
 */
public class SentencesConverterImpl implements SentencesConverter {

    public InputStream inputStream;

    public Map<OutputType, OutputStream> outputStreams = new HashMap<>();

    @Override
    public void setInputStream(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream);
        this.inputStream = inputStream;
    }

    @Override
    public void addOutputStream(OutputStream outputStream, OutputType outputType) {
        Preconditions.checkNotNull(outputStream);
        Preconditions.checkNotNull(outputType);
        this.outputStreams.put(outputType, outputStream);
    }

    @Override
    public void process() {

    }
}
