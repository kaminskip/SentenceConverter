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

    private final List<Word> words;

    private Sentence(Builder builder) {
        this.words = builder.words;
        this.sentenceNumber = builder.sentenceNumber;
    }

    public List<Word> getSortedWords(){
        List<Word> wordList = new ArrayList<>(this.words);
        Collections.sort(wordList);
        return wordList;
    }

    public void orderWords(){
        Collections.sort(this.words);
    }

    public List<Word> getWords(){
        return this.words;
    }

    public int getNumberOfWords(){
        return words.size();
    }

    public long getSentenceNumber(){
        return this.sentenceNumber;
    }

    public static class Builder {

        private List<Word> words = new ArrayList<>(5);

        private int sentenceNumber;

        public Builder(int sentenceNumber) {
            this.sentenceNumber = sentenceNumber;
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
