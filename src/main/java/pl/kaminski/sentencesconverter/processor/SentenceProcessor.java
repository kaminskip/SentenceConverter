package pl.kaminski.sentencesconverter.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kaminski.sentencesconverter.context.ReadingSentencesContext;
import pl.kaminski.sentencesconverter.model.Sentence;

/**
 * Processor changing order in sentence object
 */
@Component
public class SentenceProcessor implements ItemProcessor<Sentence, Sentence> {

    private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);

    @Override
    public Sentence process(final Sentence sentence) throws Exception {
        log.debug("Process sentence " + sentence.getSentenceNumber() + " with " + sentence.getNumberOfWords() + " words.");
        sentence.orderWords();
        return sentence;
    }
}
