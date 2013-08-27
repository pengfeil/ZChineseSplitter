package com.amazonia.chinese.splitter.file;

import com.amazonia.chinese.splitter.elements.Sentence;

/**
 * Interface for parsing each line in corpus.
 * @author pengfeil
 *
 */
public interface ILineParser {
	public Sentence[] parseLine(String line);
}
