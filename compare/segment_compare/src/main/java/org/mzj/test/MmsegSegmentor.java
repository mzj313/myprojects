package org.mzj.test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;

public class MmsegSegmentor implements Segmentor {

	public List<Word> seg(String text) {
		List<Word> list = new ArrayList<Word>();
		try {
			Dictionary dic = Dictionary.getInstance();
			Seg seg = null;
			// seg = new SimpleSeg(dic);
			seg = new ComplexSeg(dic);
			MMSeg mmSeg = new MMSeg(new StringReader(text), seg);
			com.chenlb.mmseg4j.Word word = null;
			while ((word = mmSeg.next()) != null) {
				list.add(new Word(word.getString(), word.getDegree() > 1 ? 0 : 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
