package pl.kaminski.sentencesconverter.writer;

import com.google.common.base.Joiner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class CSVHeaderWriter implements InitializingBean {

    protected static final Log logger = LogFactory.getLog(CSVHeaderWriter.class);

    @Value("${output.filename}")
    private String outputFileName;

    @Autowired
    private ReadingSentencesContext context;

    private Resource resource;

    public CSVHeaderWriter() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(new FileSystemResource(outputFileName));
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void addHeader() throws IOException {
        String header = ", " + IntStream.range(0, context.getMaxWordsInSentence()).boxed()
                .map(i -> ("Word " + (i + 1)))
                .collect(Collectors.joining(", ")) + "\n";
        logger.debug("Write header: " + header);
        RandomAccessFile file = new RandomAccessFile(resource.getFile(), "rw");
        file.seek(0);
        file.write(header.getBytes());
        file.close();
    }

    public void setContext(ReadingSentencesContext context) {
        this.context = context;
    }
}
