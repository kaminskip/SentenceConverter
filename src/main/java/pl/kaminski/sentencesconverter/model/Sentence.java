package pl.kaminski.sentencesconverter.model;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sentence value object
 */
public class Sentence {

    //sentence number
    private long sentenceNumber = 0;

    //words in sentence
    private final List<Word> words;

    //prevent instanced object
    private Sentence(Builder builder) {
        this.words = builder.words;
        this.sentenceNumber = builder.sentenceNumber;
    }

    public List<Word> getSortedWords(){
        List<Word> wordList = new ArrayList<>(this.words);
        Collections.sort(wordList);
        return wordList;
    }

    /**
     * Order words in sentence object
     */
    public void orderWords(){
        Collections.sort(this.words);
    }

    /**
     * Get words from sentence
     * @return words list
     */
    public List<Word> getWords(){
        return this.words;
    }

    /**
     * Get number of words
     * @return number of words
     */
    public int getNumberOfWords(){
        return words.size();
    }

    /**
     * Get number of sentence in processed file
     * @return number of sentence
     */
    public long getSentenceNumber(){
        return this.sentenceNumber;
    }

    /**
     * Sentence object builder
     */
    public static class Builder {

        private List<Word> words = new ArrayList<>(5);

        private int sentenceNumber;

        /**
         * Create builder
         * @param sentenceNumber number of sentence
         */
        public Builder(int sentenceNumber) {
            this.sentenceNumber = sentenceNumber;
        }

        /**
         * Add word to sentence
         * @param word word
         * @return sentence builder
         */
        public Builder addWord(String word){
            words.add(new Word.Builder().word(word).build());
            return this;
        }

        /**
         * Build sentence
         * @return sentence
         */
        public Sentence build() {
            Preconditions.checkArgument(this.words.size() > 0);
            return new Sentence(this);
        }
    }
}
