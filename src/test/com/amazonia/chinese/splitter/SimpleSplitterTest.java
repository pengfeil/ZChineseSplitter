package com.amazonia.chinese.splitter;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Word;

public class SimpleSplitterTest {
	@Test
	public void test() throws IOException {
		String string = "北京大学生";
		SimpleSplitter ss = new SimpleSplitter(Constants.MODEL_FOLDER);
		Word[] words = ss.parse(string);
		for (Word w : words) {
			Logger.log(getClass(), w.getWord());
		}
	}
}
