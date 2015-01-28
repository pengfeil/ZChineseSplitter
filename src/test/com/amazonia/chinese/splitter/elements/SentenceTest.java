package com.amazonia.chinese.splitter.elements;

import junit.framework.Assert;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Logger;

public class SentenceTest {
	@Test
	public void testGetWords() {
		String str = "鞋子很贴脚，底也不是很硬，穿着很舒服，关键是价格便宜啊。卡帕的东西假货太多了，真的还真不错，在亚马逊买的卡帕衣服，鞋子，裤子都不错。";
		doTest(str);
		String string = "asdasd asdasd1ad asdasd";
		doTest(string);
	}

	private void doTest(String str) {
		Sentence s = new Sentence(str);
		for (Word w : s.getWords()) {
			Logger.log(getClass(), w.getWord());
		}
		Assert.assertTrue(s.getWords().size() > 1);
	}
}
