package com.amazonia.chinese.splitter;

import java.io.IOException;

import com.amazonia.chinese.splitter.elements.Word;

/**
 * Main class for split
 * 
 * @author pengfeil
 * 
 */
public abstract class AbstractSplitter {
	protected Data data;

	public AbstractSplitter(String folder) throws IOException {
		this.data = Data.loadData(folder);
	}

	/**
	 * Main entrance for split
	 * 
	 * @param string
	 *            :The string to be parsed
	 * @return
	 */
	public abstract Word[] parse(String string) throws IOException;
}
