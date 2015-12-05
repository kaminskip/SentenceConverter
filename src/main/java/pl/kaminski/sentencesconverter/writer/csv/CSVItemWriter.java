package pl.kaminski.sentencesconverter.writer.csv;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.model.Sentence;

/**
 * CSV item writer, write sentences in csv
 */
@Component
public class CSVItemWriter extends FlatFileItemWriter<Sentence> {

    @Value("${output.csv.filename}")
    private String outputFileName;

    /**
     * Create writer with predefined CSV line mapper
     */
    public CSVItemWriter() {
        setExecutionContextName(ClassUtils.getShortName(CSVItemWriter.class));
        setLineAggregator(new CSVLineAggregator());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        setResource(new FileSystemResource(outputFileName));
    }
}
