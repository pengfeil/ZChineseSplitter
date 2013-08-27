package com.amazonia.chinese.splitter.elements;

import junit.framework.Assert;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Logger;

public class SentenceTest {
	@Test
	public void testGetWords() {
		String str = "������λ���fԺʿ�����������������塡�ƣ�����������������λ��ͨ�š�Ժʿ�������ã�����������硡�ƣ�������������������λ��ͨ�š��fԺʿ�������ã�����������硡�����������塡�ƣ������������M�ɡ���������������һ�ž����ꡡ���x���ߡ�������";
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
