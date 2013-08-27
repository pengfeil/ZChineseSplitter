package com.amazonia.chinese.splitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.elements.ConcatWords;
import com.amazonia.chinese.splitter.elements.Sentence;
import com.amazonia.chinese.splitter.elements.Word;
import com.amazonia.chinese.splitter.file.ILineParser;
import com.amazonia.chinese.splitter.file.Reader;

/**
 * Data container for splitter
 * 
 * @author pengfeil
 * 
 */
public class Data {
	private HashMap<Word, Double> dicHashMap = new HashMap<Word, Double>();
	private HashMap<ConcatWords, Double> concatWordHashMap = new HashMap<ConcatWords, Double>();
	private String dataFolder;
	private static Data instance;

	public static Data loadData(String folder) throws IOException {
		checkModelFile(folder);
		instance = (instance == null ? new Data() : instance);
		instance.dataFolder = folder;
		return instance;
	}

	/**
	 * Lazy load for quick test
	 * 
	 * @return
	 * @throws IOException
	 */
	public HashMap<Word, Double> getDicHashMap() throws IOException {
		if (dicHashMap.size() <= 0) {
			loadWordModel(instance, dataFolder);
		}
		return dicHashMap;
	}

	/**
	 * Lazy load for quick test
	 * 
	 * @return
	 * @throws IOException
	 */
	public HashMap<ConcatWords, Double> getConcatWordHashMap()
			throws IOException {
		if (concatWordHashMap.size() <= 0) {
			loadConcatModel(instance, dataFolder);
		}
		return concatWordHashMap;
	}

	private static void loadConcatModel(Data data, String folder)
			throws IOException {
		String file = findModelByType(Constants.CONCAT_MODEL_SUFFIX, folder);
		Reader reader = buildReader(file);
		Sentence tmp[] = null;
		while ((tmp = reader.readLine()) != null) {
			if (tmp.length != 3)
				continue;
			String preWord = tmp[0].getRawContent();
			String afterWord = tmp[1].getRawContent();
			double rate = Double.parseDouble(tmp[2].getRawContent());
			data.concatWordHashMap.put(new ConcatWords(new Word(preWord,
					Constants.NON_MARKER), new Word(afterWord,
					Constants.NON_MARKER)), rate);
		}
		reader.close();
	}

	private static void loadWordModel(Data data, String folder)
			throws IOException {
		String file = findModelByType(Constants.WORD_MODEL_SUFFIX, folder);
		Reader reader = buildReader(file);
		Sentence tmp[] = null;
		while ((tmp = reader.readLine()) != null) {
			if (tmp.length != 2)
				continue;
			String word = tmp[0].getRawContent();
			double rate = Double.parseDouble(tmp[1].getRawContent());
			data.dicHashMap.put(new Word(word, Constants.NON_MARKER), rate);
		}
		reader.close();
	}

	private static Reader buildReader(String file)
			throws UnsupportedEncodingException, FileNotFoundException {
		Reader reader = new Reader(file, "utf-8", new ILineParser() {
			@Override
			public Sentence[] parseLine(String line) {
				String[] splitsStrings = line
						.split(Constants.MODEL_SPLIT_STRING);
				Sentence[] ss = new Sentence[splitsStrings.length];
				for (int i = 0; i < ss.length; i++) {
					ss[i] = new Sentence(splitsStrings[i]);
				}
				return ss;
			}
		});
		return reader;
	}

	private static String findModelByType(String suffix, String folder) {
		File folderFile = new File(folder);
		for (String fString : folderFile.list()) {
			if (fString.endsWith(suffix)) {
				return folder + fString;
			}
		}
		throw new RuntimeException("Invalid model");
	}

	private static void checkModelFile(String path) {
		File folderFile = new File(path);
		if (!folderFile.isDirectory() || !folderFile.exists()) {
			throw new RuntimeException("Invalid model");
		}
	}
}
