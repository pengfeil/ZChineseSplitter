package com.amazonia.chinese.splitter.elements;

/**
 * Used for sorting words by rate
 * 
 * @author pengfeil
 * 
 */
public class WordAndCounter {
	private Word word;
	private ConcatWords concatWords;
	private Counter counter;

	public WordAndCounter(Word word, Counter counter) {
		this.word = word;
		this.counter = counter;
	}

	public WordAndCounter(ConcatWords concatWords, Counter counter) {
		this.concatWords = concatWords;
		this.counter = counter;
	}

	public ConcatWords getConcatWords() {
		return concatWords;
	}

	public Word getWord() {
		return word;
	}

	public Counter getCounter() {
		return counter;
	}

}
