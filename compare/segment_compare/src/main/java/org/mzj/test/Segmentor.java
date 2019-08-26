package org.mzj.test;

import java.util.List;

public interface Segmentor {
	/**
	 * 分词
	 * @param text
	 * @return
	 */
	public List<Word> seg(String text);
}
