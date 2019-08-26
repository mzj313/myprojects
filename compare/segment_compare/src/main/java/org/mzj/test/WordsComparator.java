package org.mzj.test;

import java.util.List;

public abstract class WordsComparator {
	public boolean bLog = false;

	/**
	 * 比较两个分词集合
	 * @param l1
	 * @param l2
	 * @return 相似度
	 */
	public int compare(List<Word> l1, List<Word> l2) {
		long begin = System.currentTimeMillis();
		int result = doCompare(l1, l2);
		long end = System.currentTimeMillis();
		System.out.println("相似度：" + result + " 耗时：" + (end-begin) + "ms");
		return result;
	}
	
	/**
	 * 比较两个分词集合
	 * @param l1
	 * @param l2
	 * @return 相似度
	 */
	public abstract int doCompare(List<Word> l1, List<Word> l2);
	
	/**
	 * 计算两个分词集合的相似度，将对应位置分词相似度相加
	 * @param l1
	 * @param l2
	 * @return
	 */
	public int calc(List<Word> l1, List<Word> l2) {
		int similarity = 0;
		int len = l1.size() < l2.size() ? l1.size() : l2.size();
		for (int i = 0; i < len; i++) {
			similarity += calc(l1.get(i), l2.get(i));
		}
		return similarity;
	}
	public int calc(Word[] l1, Word[] l2) {
		int similarity = 0;
		int len = l1.length < l2.length ? l1.length : l2.length;
		for (int i = 0; i < len; i++) {
			similarity += calc(l1[i], l2[i]);
		}
		return similarity;
	}
	
	/**
	 * 计算两个分词的相似度
	 * @param w1
	 * @param w2
	 * @return
	 */
	protected abstract int calc(Word w1, Word w2);
}
