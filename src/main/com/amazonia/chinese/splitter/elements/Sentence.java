package com.amazonia.chinese.splitter.elements;

import java.util.ArrayList;
import java.util.List;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;

public class Sentence {
	private String rawContent;
	private List<Word> words;
	private List<ConcatWords> concatWords;

	public Sentence(String rawContent) {
		this.rawContent = rawContent;
		this.rawContent.replaceAll("\\s+", "\\s");
		this.rawContent.replaceAll("\t+", "\\s");
		this.rawContent.trim();
	}

	public String getRawContent() {
		return rawContent;
	}

	public List<Word> getWords() {
		if (words == null) {
			readWords();
		}
		return words;
	}

	public List<ConcatWords> getConcatWords() {
		if (concatWords == null) {
			readConcats();
		}
		return concatWords;
	}

	private void readConcats() {
		concatWords = new ArrayList<ConcatWords>();
		List<Word> rawWords = getWords();
		if (rawWords.size() <= 1)
			return;
		for (int i = 0, j = 1; j < rawWords.size(); i++, j++) {
			concatWords.add(new ConcatWords(rawWords.get(i), rawWords.get(j)));
			Logger.totalConcatCounter++;
		}
	}

	/**
	 * <Word/Marker> and \<Word\> are acceptable here
	 */
	private void readWords() {
		words = new ArrayList<Word>();
		String[] rawWords = rawContent.split(Constants.WORD_SPLITTER);
		for (String rawWord : rawWords) {
			Logger.totalWordCounter++;
			if (rawWord.contains(Constants.MARKER_SPLITTER)) {
				/**
				 * handle corpus which contains markers
				 */
				String[] wm = rawWord.split(Constants.MARKER_SPLITTER);
				if (wm.length != 2) {
					Logger.errorWordCounter++;
					continue;
				}
				if (Constants.CHINESE_MARKERS.contains(wm[0]))
					continue;
				words.add(new Word(wm[0], wm[1]));
			} else {
				if (rawWord.length() >= 1
						&& !Constants.CHINESE_MARKERS.contains(rawWord)) {
					words.add(new Word(rawWord, Constants.NON_MARKER));
				}
			}
		}
	}
}
