package com.amazonia.chinese.splitter.contants;

import java.io.File;
import java.util.HashSet;

public class Constants {
	public static final String WORD_MODEL_SUFFIX = ".word";
	public static final String CONCAT_MODEL_SUFFIX = ".concat";
	public static final String NON_MARKER = "NAN";
	public static final String MODEL_FOLDER = "data/model/";
	public static final String MODEL_SPLIT_STRING = "\t";
	/**
	 * splitter between words
	 */
	public static final String WORD_SPLITTER = "(\\s|¡¡)";
	/**
	 * splitter between word and marker
	 */
	public static final String MARKER_SPLITTER = "/";
	/**
	 * special markers in sentence
	 */
	public static HashSet<String> CHINESE_MARKERS;

	public static String fixFolderPath(String dataFolderString) {
		if (dataFolderString.charAt(dataFolderString.length() - 1) != File.separatorChar) {
			dataFolderString += File.separator;
		}
		return dataFolderString;
	}

	static {
		CHINESE_MARKERS = new HashSet<String>();
		CHINESE_MARKERS.add("~");
		CHINESE_MARKERS.add("!");
		CHINESE_MARKERS.add("@");
		CHINESE_MARKERS.add("#");
		CHINESE_MARKERS.add("$");
		CHINESE_MARKERS.add("%");
		CHINESE_MARKERS.add("^");
		CHINESE_MARKERS.add("&");
		CHINESE_MARKERS.add("*");
		CHINESE_MARKERS.add("(");
		CHINESE_MARKERS.add(")");
		CHINESE_MARKERS.add("-");
		CHINESE_MARKERS.add("_");
		CHINESE_MARKERS.add("+");
		CHINESE_MARKERS.add("=");
		CHINESE_MARKERS.add("{");
		CHINESE_MARKERS.add("[");
		CHINESE_MARKERS.add("}");
		CHINESE_MARKERS.add("]");
		CHINESE_MARKERS.add("|");
		CHINESE_MARKERS.add("\\");
		CHINESE_MARKERS.add(":");
		CHINESE_MARKERS.add(";");
		CHINESE_MARKERS.add("\"");
		CHINESE_MARKERS.add("'");
		CHINESE_MARKERS.add("?");
		CHINESE_MARKERS.add("/");
		CHINESE_MARKERS.add(">");
		CHINESE_MARKERS.add("<");
		CHINESE_MARKERS.add(".");
		CHINESE_MARKERS.add(",");

		CHINESE_MARKERS.add("¡«");
		CHINESE_MARKERS.add("¡¤");
		CHINESE_MARKERS.add("£¡");
		CHINESE_MARKERS.add("@");
		CHINESE_MARKERS.add("#");
		CHINESE_MARKERS.add("£¤");
		CHINESE_MARKERS.add("%");
		CHINESE_MARKERS.add("¡­¡­");
		CHINESE_MARKERS.add("&");
		CHINESE_MARKERS.add("¡Á");
		CHINESE_MARKERS.add("£¨");
		CHINESE_MARKERS.add("£©");
		CHINESE_MARKERS.add("-");
		CHINESE_MARKERS.add("¡ª¡ª");
		CHINESE_MARKERS.add("+");
		CHINESE_MARKERS.add("=");
		CHINESE_MARKERS.add("¡º");
		CHINESE_MARKERS.add("¡¾");
		CHINESE_MARKERS.add("¡»");
		CHINESE_MARKERS.add("¡¿");
		CHINESE_MARKERS.add("¡¢");
		CHINESE_MARKERS.add("|");
		CHINESE_MARKERS.add("¡°");
		CHINESE_MARKERS.add("¡®");
		CHINESE_MARKERS.add("£º");
		CHINESE_MARKERS.add("£»");
		CHINESE_MARKERS.add("£¿");
		CHINESE_MARKERS.add("/");
		CHINESE_MARKERS.add("¡·");
		CHINESE_MARKERS.add("¡¶");
		CHINESE_MARKERS.add("£¬");
		CHINESE_MARKERS.add("¡£");
		CHINESE_MARKERS.add("¡±");
		CHINESE_MARKERS.add("¡¹");
		CHINESE_MARKERS.add("¡¸");
	}
}
