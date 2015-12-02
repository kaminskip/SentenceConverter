package pl.kaminski.sentencesconverter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
@Configuration
@EnableBatchProcessing
public class AppConfiguration {

    @Bean
    public Job convertSentencesJob(JobBuilderFactory jobs, Step step, JobExecutionListener listener) {
        return jobs.get("convertSentencesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step convertSentencesStep(StepBuilderFactory stepBuilderFactory, ItemReader<List<String>> reader, ItemProcessor<List<String>, Sentence> processor) {
        return stepBuilderFactory.get("convertSentencesStep")
                .<List<String>, Sentence> chunk(1)
                .reader(reader)
                .processor(processor)
                .build();
    }

}
