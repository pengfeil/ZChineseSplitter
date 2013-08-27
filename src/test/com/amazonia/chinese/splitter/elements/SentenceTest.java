package com.amazonia.chinese.splitter.elements;

import junit.framework.Assert;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Logger;

public class SentenceTest {
	@Test
	public void testGetWords() {
		String str = "６６　位　協院士　（　Ａｓｓｏｃｉａｔｅ　Ｆｅｌｌｏｗ　）　２４　位　通信　院士　（　Ｃｏｒｒｅｓｐｏｎｄｉｎｇ　Ｆｅｌｌｏｗ　）　及　２　位　通信　協院士　（　Ｃｏｒｒｅｓｐｏｎｄｉｎｇ　Ａｓｓｏｃｉａｔｅ　Ｆｅｌｌｏｗ　）　組成　（　不　包括　一九九四年　當選　者　）　，";
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
