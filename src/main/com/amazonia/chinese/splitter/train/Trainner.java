package com.amazonia.chinese.splitter.train;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.amazonia.chinese.splitter.contants.Constants;
import com.amazonia.chinese.splitter.contants.Logger;
import com.amazonia.chinese.splitter.elements.ConcatWords;
import com.amazonia.chinese.splitter.elements.Counter;
import com.amazonia.chinese.splitter.elements.Sentence;
import com.amazonia.chinese.splitter.elements.Word;
import com.amazonia.chinese.splitter.elements.WordAndCounter;
import com.amazonia.chinese.splitter.file.Reader;
import com.amazonia.chinese.splitter.file.ILineParser;

/**
 * Main controller of training process
 * 
 * @author pengfeil
 * 
 */
public class Trainner {
	private HashMap<Word, Counter> dicHashMap;
	private HashMap<ConcatWords, Counter> concatWordHashMap;
	private String dataFolderString;
	private String charset;
	private ILineParser lineParser;

	/**
	 * Create a trainer to get data model
	 * 
	 * @param dataFolderString
	 *            input file path. Can be a file or a folder without folder
	 *            inside.
	 * @param charset
	 * @param lineParser
	 *            implementation of ILineParser
	 * @throws IOException
	 */
	public Trainner(String dataFolderString, String charset,
			ILineParser lineParser) throws IOException {
		this.dataFolderString = Constants.fixFolderPath(dataFolderString);
		dicHashMap = new HashMap<Word, Counter>();
		concatWordHashMap = new HashMap<ConcatWords, Counter>();
		this.charset = charset;
		this.lineParser = lineParser;
	}

	public void run() throws IOException {
		String[] files = getDataFileNames();
		for (String path : files) {
			Logger.log(getClass(), "Reading " + path);
			countWordFrequency(dataFolderString + path);
		}
		/**
		 * count words
		 */
		List<WordAndCounter> dicList = calculateRateAndSort(getWordList());
		printSummary(dicList);
		outputWordRate(dicList);

		/**
		 * count concat words
		 */
		List<WordAndCounter> concatList = calculateRateAndSort(getConcatList());
		outputConcatRate(concatList);
	}

	private String[] getDataFileNames() {
		File folder = new File(dataFolderString);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				return folder.list();
			} else {
				return new String[] { folder.getName() };
			}
		}
		return new String[0];
	}

	private void outputWordRate(List<WordAndCounter> dicList)
			throws IOException {
		PrintWriter pWriter = new PrintWriter(Constants.MODEL_FOLDER
				+ (new Date()).getTime() + Constants.WORD_MODEL_SUFFIX, "utf-8");
		for (WordAndCounter wordAndCounter : dicList) {
			if (wordAndCounter.getCounter().getRate() <= 0)
				continue;
			pWriter.println(wordAndCounter.getWord().getWord()
					+ Constants.MODEL_SPLIT_STRING
					+ wordAndCounter.getCounter().getRate());
		}
		pWriter.close();
	}

	private void outputConcatRate(List<WordAndCounter> concatList)
			throws IOException {
		PrintWriter pWriter = new PrintWriter(Constants.MODEL_FOLDER
				+ (new Date()).getTime() + Constants.CONCAT_MODEL_SUFFIX,
				"utf-8");
		for (WordAndCounter wordAndCounter : concatList) {
			if (wordAndCounter.getCounter().getRate() <= 0)
				continue;
			pWriter.println(wordAndCounter.getConcatWords().getPreWord()
					.getWord()
					+ Constants.MODEL_SPLIT_STRING
					+ wordAndCounter.getConcatWords().getAfterWord().getWord()
					+ Constants.MODEL_SPLIT_STRING
					+ wordAndCounter.getCounter().getRate());
		}
		pWriter.close();
	}

	private void printSummary(List<WordAndCounter> dicList) {
		Logger.log(getClass(), "top word:" + dicList.get(0).getWord().getWord()
				+ ": " + dicList.get(0).getCounter().getNumber() + "/"
				+ dicList.get(0).getCounter().getRate());
		Logger.log(getClass(), "total record: " + dicList.size());
		Logger.log(getClass(), "total error: " + Logger.errorWordCounter);
		Logger.log(getClass(), "total word count: " + Logger.totalWordCounter);
		Logger.log(getClass(), "total line: " + Logger.totalLineCounter);
	}

	private List<WordAndCounter> calculateRateAndSort(List<WordAndCounter> list) {
		Collections.sort(list, new Comparator<WordAndCounter>() {
			@Override
			public int compare(WordAndCounter c1, WordAndCounter c2) {
				double n1 = c1.getCounter().getRate();
				double n2 = c2.getCounter().getRate();
				if (n1 < n2)
					return 1;
				else if (n1 > n2)
					return -1;
				return 0;
			}
		});
		return list;
	}

	private List<WordAndCounter> getWordList() {
		List<WordAndCounter> list = new ArrayList<WordAndCounter>();
		Set<Word> keySet = dicHashMap.keySet();
		for (Word word : keySet) {
			Counter counter = dicHashMap.get(word);
			list.add(new WordAndCounter(word, counter));
		}
		dicHashMap.clear();
		return list;
	}

	private List<WordAndCounter> getConcatList() {
		List<WordAndCounter> list = new ArrayList<WordAndCounter>();
		Set<ConcatWords> keySet = concatWordHashMap.keySet();
		for (ConcatWords cw : keySet) {
			Counter counter = concatWordHashMap.get(cw);
			list.add(new WordAndCounter(cw, counter));
		}
		concatWordHashMap.clear();
		return list;
	}

	private void countWordFrequency(String path) throws IOException {
		Reader corpusReader = new Reader(path, charset, lineParser);
		Sentence tmp[] = null;
		while ((tmp = corpusReader.readLine()) != null) {
			for (Sentence sentence : tmp) {
				for (Word w : sentence.getWords()) {
					increaWordCounter(w);
				}
				for (ConcatWords cw : sentence.getConcatWords()) {
					increaConcatCounter(cw);
				}
			}
		}
		corpusReader.close();
	}

	private void increaWordCounter(Word w) {
		if (dicHashMap.containsKey(w)) {
			dicHashMap.get(w).increase();
		} else {
			dicHashMap.put(w, new Counter());
		}
	}

	private void increaConcatCounter(ConcatWords w) {
		if (concatWordHashMap.containsKey(w)) {
			concatWordHashMap.get(w).increase();
		} else {
			concatWordHashMap.put(w, new Counter(true));
		}
	}
}
