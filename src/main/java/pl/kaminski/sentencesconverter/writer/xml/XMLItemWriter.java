package pl.kaminski.sentencesconverter.writer.xml;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.model.Sentence;

/**
 * XML item writer, write sentences in xml
 */
@Component
public class XMLItemWriter extends FlatFileItemWriter<Sentence> {

    @Value("${output.xml.filename}")
    private String outputFileName;

    //Sentence tag format
    private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>";

    //Word tag format
    private static final String FOOTER = "</text>";

    /**
     * Create writer with configured line aggregator
     */
    public XMLItemWriter() {
        setExecutionContextName(ClassUtils.getShortName(XMLItemWriter.class));
        setLineAggregator(new XMLLineAggregator());
        setHeaderCallback(writer -> writer.append(HEADER));
        setFooterCallback(writer -> writer.append(FOOTER));
    }

    /**
     * Set resource from application.properties
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        setResource(new FileSystemResource(outputFileName));
    }
}
