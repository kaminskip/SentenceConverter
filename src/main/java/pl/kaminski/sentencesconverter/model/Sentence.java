package pl.kaminski.sentencesconverter.model;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paweł Kamiński.
 */
public class Sentence {

    private long sentenceNumber = 0;

    private static long numberOfSentences = 0;

    private List<Word> words;

    private Sentence(Builder builder) {
        this.words = builder.words;
        Collections.sort(this.words);
        this.sentenceNumber = numberOfSentences;
    }

    public List<Word> getWords(){
        return words;
    }

    public int getNumberOfWords(){
        return words.size();
    }

    public long getSentenceNumber(){
        return this.sentenceNumber;
    }

    public static class Builder {

        private List<Word> words = new ArrayList<>(5);

        public Builder() {
            numberOfSentences++;
        }

        public Builder addWord(String word){
            words.add(new Word.Builder().word(word).build());
            return this;
        }

        public Sentence build() {
            Preconditions.checkArgument(this.words.size() > 0);
            return new Sentence(this);
        }
    }
}
