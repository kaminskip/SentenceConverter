package pl.kaminski.sentencesconverter.writer.csv;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CSV item writer, write sentences in csv
 */
@Component
public class CSVItemWriter extends FlatFileItemWriter<Sentence> {

    @Value("${output.csv.filename}")
    private String outputFileName;

    @Autowired
    private ReadingSentencesContext context;

    /**
     * Create writer with predefined CSV line mapper
     */
    public CSVItemWriter() {
        setExecutionContextName(ClassUtils.getShortName(CSVItemWriter.class));
        setLineAggregator(new CSVLineAggregator());
        setHeaderCallback(writer -> writer.append(generateHeader()));
    }

    /**
     * Create header with maximum words in all sentences
     * @return header content
     */
    private String generateHeader(){
        return ", " + IntStream.range(0, context.getMaxWordsInSentence()).boxed()
                .map(i -> ("Word " + (i + 1)))
                .collect(Collectors.joining(", "));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        setResource(new FileSystemResource(outputFileName));
    }

    public void setContext(ReadingSentencesContext context) {
        this.context = context;
    }
}
