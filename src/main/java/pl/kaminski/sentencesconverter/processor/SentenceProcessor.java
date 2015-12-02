package pl.kaminski.sentencesconverter.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.model.Sentence;

import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
@Component
public class SentenceProcessor implements ItemProcessor<List<String>, Sentence> {

    private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);

    @Override
    public Sentence process(final List<String> wordList) throws Exception {
        Sentence.Builder builder = new Sentence.Builder();
        for (String word: wordList){
            builder.addWord(word);
        }
        Sentence sentence = builder.build();
        log.info("Process sentence " + sentence.getSentenceNumber() + " with " + sentence.getNumberOfWords() + " words.");
        return builder.build();
    }
}
