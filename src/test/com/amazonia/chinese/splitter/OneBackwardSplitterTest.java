package com.amazonia.chinese.splitter;

import java.io.IOException;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Word;

public class OneBackwardSplitterTest {
	@Test
	public void test() throws IOException {
		String string = "Ь�Ӻ����ţ���Ҳ���Ǻ�Ӳ�����ź�������ؼ��Ǽ۸���˰��������Ķ����ٻ�̫���ˣ���Ļ��治��������ѷ��Ŀ����·���Ь�ӣ����Ӷ�����";
		OneBackwardSplitter obs = new OneBackwardSplitter(Constants.MODEL_FOLDER);
		Word[] words = obs.parse(string);
		for (Word w : words) {
			Logger.log(getClass(), w.getWord());
		}
	}
}
