package com.amazonia.chinese.splitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.ConcatWords;
import com.amazonia.chinese.splitter.elements.Word;

public class OneBackwardSplitter extends AbstractSplitter {

	public OneBackwardSplitter(String folder) throws IOException {
		super(folder);
	}

	@Override
	public Word[] parse(String string) throws IOException {
		List<Word> words = new ArrayList<Word>();
		char[] chars = string.toCharArray();
		int jRecord = -1;
		for (int i = 0; i < chars.length - 1;) {
			double maxRate = -1;
			jRecord = i;
			for (int j = i; j < chars.length; j++) {
				String cw = new String(chars, i, j - i + 1);
				Word word = new Word(cw, Constants.NON_MARKER);
				double P_A_B = -1;
				if (i == 0 || P(words.get(words.size() - 1)) <= 0) {
					P_A_B = P(word);
				} else {
					double P_B = P(words.get(words.size() - 1));
					double P_AB = P(words.get(words.size() - 1), word);
					if (P_B > 0 && P_AB > 0)
						P_A_B = P_AB / P_B;
					else
						P_A_B = -1;
					Logger.log(getClass(), "P_A_B:" + P_A_B);
				}
				if (P_A_B > maxRate) {
					maxRate = P_A_B;
					jRecord = j;
				}
			}
			words.add(new Word(new String(chars, i, jRecord - i + 1),
					Constants.NON_MARKER));
			i = jRecord + 1;
		}
		if (jRecord < chars.length - 1) {
			words.add(new Word(new String(chars, jRecord + 1, chars.length - 1
					- jRecord), Constants.NON_MARKER));
		}
		return words.toArray(new Word[0]);
	}

	/**
	 * reduce the rate of single-word
	 * 
	 * @param rate
	 * @return
	 */
	private double reduceRate(double rate) {
		return 0;
	}

	private double P(Word word) throws IOException {
		double rate = -1;
		if (data.getDicHashMap().containsKey(word)) {
			rate = data.getDicHashMap().get(word);
		}
		if (word.getWord().length() <= 1) {
			rate = reduceRate(rate);
		}
		return rate;
	}

	private double P(Word preWord, Word afterWord) throws IOException {
		ConcatWords cw = new ConcatWords(preWord, afterWord);
		double rate = -1;
		if (data.getConcatWordHashMap().containsKey(cw)) {
			rate = data.getConcatWordHashMap().get(cw);
		}
		if (afterWord.getWord().length() <= 1) {
			rate = reduceRate(rate);
		}
		return rate;
	}
}
