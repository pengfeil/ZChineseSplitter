package com.amazonia.chinese.splitter.contants;

import java.io.PrintStream;
import java.util.Date;

/**
 * Simple logger utils. Should be replaced by log4j latter.
 * 
 * @author pengfeil
 * 
 */
public class Logger {
	public static long errorWordCounter = 0;
	public static long totalWordCounter = 0;
	public static long totalConcatCounter = 0;
	public static long totalLineCounter = 0;
	
	public static void log(Class<?> cls, String msg) {
		out(System.out, cls, msg);
	}

	public static void warn(Class<?> cls, String msg) {
		out(System.out, cls, "[WARN]" + msg);
	}

	public static void error(Class<?> cls, String msg) {
		out(System.err, cls, msg);
	}

	@SuppressWarnings("deprecation")
	public static void out(PrintStream out, Class<?> cls, String msg) {
		out.print((new Date()).toLocaleString() + ":");
		out.print(cls.toString());
		out.print(":");
		out.print(msg);
		out.println();
	}
}
