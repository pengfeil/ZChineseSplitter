package com.amazonia.chinese.splitter.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.Sentence;

/**
 * Main entrance for reading corpus.
 * 
 * @author pengfeil
 * 
 */
public class Reader {
	private String filePath;
	private BufferedReader reader;
	private String charset;
	private ILineParser lineParser;

	/**
	 * Build a CorpusReader with file path and file charset
	 * 
	 * @param path
	 * @param charset
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public Reader(String path, String charset, ILineParser lineParser)
			throws UnsupportedEncodingException, FileNotFoundException {
		this.filePath = path;
		this.charset = charset;
		this.lineParser = lineParser;
		initialize();
	}

	private void initialize() throws UnsupportedEncodingException,
			FileNotFoundException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(
				filePath), charset));
	}

	public Sentence[] readLine() throws IOException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		Logger.totalLineCounter++;
		return lineParser.parseLine(line);
	}

	public void close() throws IOException {
		reader.close();
	}

	/**
	 * Close and reopen the file
	 * 
	 * @throws IOException
	 */
	public void reset() throws IOException {
		reader.close();
		initialize();
	}
}
