package pl.kaminski.sentencesconverter.writer;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.model.Sentence;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class CSVItemWriter extends FlatFileItemWriter<Sentence> {

    @Value("${output.filename}")
    private String outputFileName;

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
