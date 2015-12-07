package pl.kaminski.sentencesconverter.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.processor.SentenceProcessor;
import pl.kaminski.sentencesconverter.reader.SentenceReader;
import pl.kaminski.sentencesconverter.writer.csv.AddCSVHeaderToFileListener;
import pl.kaminski.sentencesconverter.writer.csv.CSVItemWriter;
import pl.kaminski.sentencesconverter.writer.xml.XMLItemWriter;

/**
 * Spring application configuration
 */
@Configuration
@EnableBatchProcessing
public class AppConfiguration {

    //Property from application.properties
    @Value("${output.type}")
    private String outputTypeParam;

    //Selected output type, default CSV
    OutputType outputType = OutputType.CSV;

    @Autowired
    private JobCompletionListener jobCompletionListener;

    @Autowired
    private SentenceChunkListener sentenceChunkListener;

    @Autowired
    private AddCSVHeaderToFileListener addCSVHeaderToFileListener;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SentenceReader sentenceReader;

    @Autowired
    private SentenceProcessor sentenceProcessor;

    @Autowired
    private CSVItemWriter csvItemWriter;

    @Autowired
    private XMLItemWriter xmlItemWriter;

    private Step step;

    @Bean
    public Job convertSentences(JobBuilderFactory jobs) {
        setUpOutputStep();
        if(this.outputType == OutputType.CSV){
            return jobs.get("convertSentencesToCsvJob")
                    .listener(addCSVHeaderToFileListener)
                    .listener(jobCompletionListener)
                    .flow(this.step)
                    .end()
                    .build();
        } else {
            return jobs.get("convertSentencesToCsvJob")
                    .listener(jobCompletionListener)
                    .flow(this.step)
                    .end()
                    .build();
        }
    }

    /**
     * Read application.properties and set up configured output
     */
    private void setUpOutputStep(){
        this.outputType = OutputType.valueOf(this.outputTypeParam);
        if(this.outputType == null){
            throw new IllegalArgumentException(this.outputTypeParam + " is not recognized.");
        }
        switch (this.outputType){
            case CSV: this.step = convertSentencesToCsvStep(); break;
            case XML: this.step = convertSentencesToXmlStep(); break;
        }
    }

    @Bean
    public Step convertSentencesToCsvStep() {
        return stepBuilderFactory.get("convertSentencesToCsvStep")
                .<Sentence, Sentence> chunk(10)
                .reader(sentenceReader)
                .processor(sentenceProcessor)
                .writer(csvItemWriter)
                .listener(sentenceChunkListener)
                .build();
    }

    @Bean
    public Step convertSentencesToXmlStep() {
        return stepBuilderFactory.get("convertSentencesToXmlStep")
                .<Sentence, Sentence> chunk(10)
                .reader(sentenceReader)
                .processor(sentenceProcessor)
                .writer(xmlItemWriter)
                .listener(sentenceChunkListener)
                .build();
    }

    enum OutputType {
        CSV, XML
    }
}
