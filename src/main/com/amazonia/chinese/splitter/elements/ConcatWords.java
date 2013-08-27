package com.amazonia.chinese.splitter.elements;

public class ConcatWords {
	private Word preWord;
	private Word afterWord;

	public ConcatWords(Word preWord, Word afterWord) {
		this.preWord = preWord;
		this.afterWord = afterWord;
	}

	public Word getPreWord() {
		return preWord;
	}

	public Word getAfterWord() {
		return afterWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afterWord == null) ? 0 : afterWord.hashCode());
		result = prime * result + ((preWord == null) ? 0 : preWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcatWords other = (ConcatWords) obj;
		if (afterWord == null) {
			if (other.afterWord != null)
				return false;
		} else if (!afterWord.equals(other.afterWord))
			return false;
		if (preWord == null) {
			if (other.preWord != null)
				return false;
		} else if (!preWord.equals(other.preWord))
			return false;
		return true;
	}

}
