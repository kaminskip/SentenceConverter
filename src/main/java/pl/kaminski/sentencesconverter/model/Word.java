package pl.kaminski.sentencesconverter.model;

import com.google.common.base.Preconditions;

/**
 * Created by Paweł Kamiński.
 */
public class Word implements Comparable<Word>{

    private String word;

    private Word(Builder builder) {
        this.word = builder.word;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.getWord());
    }

    public String getWord(){
        return this.word;
    }

    @Override
    public String toString() {
        return this.word;
    }

    public static class Builder {

        private String word;

        public Builder() {

        }

        public Builder word(String word) {
            this.word = word;
            return this;
        }

        public Word build() {
            Preconditions.checkNotNull(this.word);
            return new Word(this);
        }
    }
}
