package com.amazonia.chinese.splitter.train;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.elements.Sentence;
import com.amazonia.chinese.splitter.file.ILineParser;

public class TrainnerTest {
	@Test
	public void test() throws IOException {
		Trainner trainner = new Trainner("data/train", "utf-8",
				new ILineParser() {
					@Override
					public Sentence[] parseLine(String line) {
						return new Sentence[] { new Sentence(line) };
					}
				});
		trainner.run();
	}
}
