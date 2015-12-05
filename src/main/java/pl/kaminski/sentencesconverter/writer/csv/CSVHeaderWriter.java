package pl.kaminski.sentencesconverter.writer.csv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Write header (Words numbered) in CSV output file
 */
@Component
public class CSVHeaderWriter implements InitializingBean {

    protected static final Log logger = LogFactory.getLog(CSVHeaderWriter.class);

    @Value("${output.csv.filename}")
    private String outputFileName;

    @Autowired
    private ReadingSentencesContext context;

    // Resource witch data
    private Resource resource;

    // Path to temp file with header
    private Path tempHeaderFilePath;

    // Path to temp file with data
    private Path tempDataFilePath;

    // Path to resource file
    private Path filePath;

    public CSVHeaderWriter() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(new FileSystemResource(outputFileName));
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * Add header to resource
     * @throws IOException
     */
    public void addHeader() throws IOException {
        prepareTempFiles(generateHeader());
        concatenateFiles();
    }

    /**
     * Create header with maximum words in all sentences
     * @return header content
     */
    private String generateHeader(){
        return ", " + IntStream.range(0, context.getMaxWordsInSentence()).boxed()
                .map(i -> ("Word " + (i + 1)))
                .collect(Collectors.joining(", ")) + "\r\n";
    }

    /**
     * Prepare temporary files to concatenate data
     * @param header header to concatenate
     * @throws IOException
     */
    private void prepareTempFiles(String header) throws IOException {
        filePath = resource.getFile().toPath();

        //Create temp files
        tempHeaderFilePath = Files.createTempFile("out", ".header.tmp");
        tempHeaderFilePath.toFile().deleteOnExit();
        tempDataFilePath = Files.createTempFile("out", ".data.tmp");
        tempDataFilePath.toFile().deleteOnExit();

        //Copy to temp files
        Files.write(tempHeaderFilePath, header.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        Files.copy(filePath, tempDataFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Merge temporary files
     * @throws IOException
     */
    private void concatenateFiles() throws IOException {
        try(InputStream tempHeaderInputStream = new FileInputStream(tempHeaderFilePath.toFile());
            InputStream tempDataInputStream = new FileInputStream(tempDataFilePath.toFile());
            OutputStream dataOutputStream = new FileOutputStream(filePath.toFile())){
            SequenceInputStream s = new SequenceInputStream(tempHeaderInputStream, tempDataInputStream);
            int c;
            while ((c = s.read()) != -1){
                dataOutputStream.write(c);
            }
        }
    }

    public void setContext(ReadingSentencesContext context) {
        this.context = context;
    }
}
