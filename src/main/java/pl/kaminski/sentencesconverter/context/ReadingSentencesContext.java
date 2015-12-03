package pl.kaminski.sentencesconverter.context;

import org.springframework.stereotype.Component;

/**
 * Created by Paweł Kamiński.
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
