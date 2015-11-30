package pl.kaminski.sentencesconverter;

/**
 * Created by Paweł Kamiński.
 */

import java.io.InputStream;
import java.io.OutputStream;

public interface SentencesConverter {

    void setInputStream(InputStream inputStream);

    void addOutputStream(OutputStream outputStream, OutputType outputType);

    void process();

}
