package pl.kaminski.sentencesconverter.model;

import com.google.common.base.Preconditions;

/**
 * Word value object
 */
public class Word implements Comparable<Word>{

    private String word;

    //prevent instanced object
    private Word(Builder builder) {
        this.word = builder.word;
    }

    @Override
    public int compareTo(Word o) {
        int ret = this.word.compareToIgnoreCase(o.getWord());
        if(ret == 0){
            return o.getWord().compareTo(this.word);
        } else {
            return ret;
        }
    }

    /**
     * Get string value of word
     * @return string value
     */
    public String getWord(){
        return this.word;
    }

    @Override
    public String toString() {
        return this.word;
    }

    /**
     * Word value object builder
     */
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
