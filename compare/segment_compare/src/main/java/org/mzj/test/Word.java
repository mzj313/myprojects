package org.mzj.test;

public class Word {
	public String value;// 单词
	public int weight;// 权值

	public Word() {
		value = "";
		weight = 1;
	}

	public Word(String value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public String toString() {
		return value + weight;
	}
}
