package com.amazonia.chinese.splitter;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Word;

public class SimpleSplitterTest {
	@Test
	public void test() throws IOException {
		String string = "鞋子很贴脚，底也不是很硬，穿着很舒服，关键是价格便宜啊。卡帕的东西假货太多了，真的还真不错，在亚马逊买的卡帕衣服，鞋子，裤子都不错。";
		SimpleSplitter ss = new SimpleSplitter(Constants.MODEL_FOLDER);
		Word[] words = ss.parse(string);
		for (Word w : words) {
			Logger.log(getClass(), w.getWord());
		}
	}
}
