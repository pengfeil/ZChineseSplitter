package com.amazonia.chinese.splitter;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Word;

public class OneBackwardSplitterTest {
	@Test
	public void test() throws IOException {
		String string = "鞋子很贴脚，底也不是很硬，穿着很舒服，关键是价格便宜啊。卡帕的东西假货太多了，真的还真不错，在亚马逊买的卡帕衣服，鞋子，裤子都不错。";
		OneBackwardSplitter obs = new OneBackwardSplitter(Constants.MODEL_FOLDER);
		Word[] words = obs.parse(string);
		for (Word w : words) {
			Logger.log(getClass(), w.getWord());
		}
	}
}
