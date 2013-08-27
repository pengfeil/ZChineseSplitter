package com.amazonia.chinese.splitter.elements;

import com.amazonia.chinese.splitter.contants.Logger;

public class Counter {
	private long number;
	private double rate;
	private boolean isConcat;

	public Counter() {
		number = 0;
		rate = -1;
	}

	public Counter(boolean isConcat) {
		number = 0;
		rate = -1;
		this.isConcat = isConcat;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * This method should be call after the total counter finish it's work
	 * 
	 * @return
	 */
	public double getRate() {
		if (rate < 0) {
			long total = isConcat ? Logger.totalConcatCounter
					: Logger.totalWordCounter;
			rate = ((double) number) / ((double) total);
		}
		return rate;
	}

	public void increase() {
		number++;
	}

}
