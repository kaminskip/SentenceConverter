package pl.kaminski.sentencesconverter.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kaminski.sentencesconverter.model.Sentence;
import pl.kaminski.sentencesconverter.writer.AddCSVHeaderToFileListener;

/**
 * Created by Paweł Kamiński.
 */
@Configuration
@EnableBatchProcessing
public class AppConfiguration {

    @Value("${output.type}")
    private String outputType;

    @Autowired
    private JobCompletionListener jobCompletionListener;

    @Autowired
    private AddCSVHeaderToFileListener addCSVHeaderToFileListener;

    @Bean
    public Job convertSentencesJob(JobBuilderFactory jobs, Step step) {
        return jobs.get("convertSentencesJob")
                .incrementer(new RunIdIncrementer())
                .listener(addCSVHeaderToFileListener)
                .listener(jobCompletionListener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step convertSentencesStep(StepBuilderFactory stepBuilderFactory, ItemReader<Sentence> reader, ItemProcessor<Sentence, Sentence> processor, ItemWriter<Sentence> writer) {
        return stepBuilderFactory.get("convertSentencesStep")
                .<Sentence, Sentence> chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
