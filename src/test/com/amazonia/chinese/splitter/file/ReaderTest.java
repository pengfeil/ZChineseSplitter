package com.amazonia.chinese.splitter.file;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Sentence;

public class ReaderTest {
	@Test
	public void test() throws IOException {
		Reader cr = new Reader("data/train/pku_training.utf8",
				"utf-8", new ILineParser() {
					@Override
					public Sentence[] parseLine(String line) {
						return new Sentence[] { new Sentence(line) };
					}
				});
		int count = 0;
		while ((cr.readLine()) != null) {
			count++;
		}
		Logger.log(getClass(), "total line:" + count);
	}
}
