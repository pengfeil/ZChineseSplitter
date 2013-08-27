package com.amazonia.chinese.splitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.elements.Word;

/**
 * A simple implementation. Find the max rate word from head to tail in
 * sequence.
 * 
 * @author pengfeil
 * 
 */
public class SimpleSplitter extends AbstractSplitter {

	public SimpleSplitter(String folder) throws IOException {
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
				double rate = -1;
				if (data.getDicHashMap().containsKey(word)) {
					rate = data.getDicHashMap().get(word);
				}
				if (cw.length() <= 1) {
					rate = reduceRate(rate);
				}
				if (rate > maxRate) {
					maxRate = rate;
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
}
