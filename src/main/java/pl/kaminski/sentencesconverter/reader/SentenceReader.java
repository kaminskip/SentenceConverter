package pl.kaminski.sentencesconverter.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.Scanner;

/**
 * Read file form resource
 */
@Component
public class SentenceReader extends AbstractItemCountingItemStreamItemReader<Sentence> implements
        ResourceAwareItemReaderItemStream<Sentence>, InitializingBean {

    @Value("${input.filename}")
    private String inputFileName;

    private static final Log logger = LogFactory.getLog(SentenceReader.class);

    // default encoding for input files
    public static final String DEFAULT_CHARSET = "UTF-8";

    // file encoding
    private String encoding = DEFAULT_CHARSET;

    // dot regexp delimiter to split text into sentences
    public static final String DOT = "(?<!Mr)\\.|!|\\?";

    // scanner for looking for the sentences
    private Scanner scanner;

    // input file resource with sentences
    private Resource resource;

    // count sentences
    private int lineCount = 0;

    // empty resource
    private boolean noInput = false;

    /**
     * Create sentence reader
     */
    public SentenceReader() {
        setName(ClassUtils.getShortName(SentenceReader.class));
    }

    @Autowired
    private LineMapper<Sentence> lineMapper;

    @Override
    protected Sentence doRead() throws Exception {
        if (noInput) {
            return null;
        }
        String sentence = "";
        if (!scanner.hasNext()) {
            return null;
        } else {
            try {
                sentence = scanner.next();
                if(sentence.length() == 0){
                    return null;
                }
                sentence = sentence.trim().replaceAll("\n|\r|-|\\(|\\)", " ");
                sentence = sentence.replace("’", "'");
                if(sentence.length() == 0){
                    return null;
                }
                lineCount++;
                return lineMapper.mapLine(sentence, lineCount);
            }
            catch (Exception ex) {
                throw new FlatFileParseException("Parsing error at line: " + lineCount + " in resource=["
                        + resource.getDescription() + "], input=[" + sentence + "]", ex, sentence, lineCount);
            }
        }
    }

    @Override
    protected void doOpen() throws Exception {
        if(resource == null){
            resource = new FileSystemResource(inputFileName);
        }
        Assert.notNull(resource, "Input resource must be set");
        noInput = true;
        if (!resource.exists()) {
            throw new IllegalStateException("Input resource must exist: " + resource);
        }

        if (!resource.isReadable()) {
            throw new IllegalStateException("Input resource must be readable: "
                    + resource);
        }

        scanner = new Scanner(resource.getInputStream(), encoding).useDelimiter(DOT);
        noInput = false;
    }

    @Override
    protected void doClose() throws Exception {
        lineCount = 0;
        if (scanner != null) {
            scanner.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(lineMapper, "LineMapper is required");
        logger.debug("Sentence reader initialized");
    }

    /**
     * Public setter for the input resource.
     */
    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * Setter for the encoding for this input source. Default value is {@link #DEFAULT_CHARSET}.
     *
     * @param encoding a properties object which possibly contains the encoding for this input file;
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Setter for line mapper. This property is required to be set.
     * @param lineMapper maps line to item
     */
    public void setLineMapper(LineMapper<Sentence> lineMapper) {
        this.lineMapper = lineMapper;
    }
}
