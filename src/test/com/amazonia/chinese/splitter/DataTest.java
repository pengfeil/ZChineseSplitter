package com.amazonia.chinese.splitter;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;

public class DataTest {
	@Test
	public void test() throws IOException {
		Data data = Data.loadData(Constants.MODEL_FOLDER);
		Assert.assertTrue(data.getDicHashMap().size() > 0);
		Assert.assertTrue(data.getConcatWordHashMap().size() > 0);
		Logger.log(getClass(), "data.getDicHashMap().size():"
				+ data.getDicHashMap().size());
		Logger.log(getClass(), "data.getConcatWordHashMap().size():"
				+ data.getConcatWordHashMap().size());
	}
}
