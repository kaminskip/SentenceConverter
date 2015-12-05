package pl.kaminski.sentencesconverter.context;

import org.springframework.stereotype.Component;

/**
 * Application context for persisting actual max words in sentence of processed sentences
 */
@Component
public class ReadingSentencesContext {

    private int maxWordsInSentence = 0;

    public void setSentenceWordsCount(int sentenceWordsCount){
        if(sentenceWordsCount > this.maxWordsInSentence){
            maxWordsInSentence = sentenceWordsCount;
        }
    }

    public int getMaxWordsInSentence(){
        return this.maxWordsInSentence;
    }
}
