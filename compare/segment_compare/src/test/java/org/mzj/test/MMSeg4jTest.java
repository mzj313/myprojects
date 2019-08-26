package org.mzj.test;

import java.io.File;
import java.io.StringReader;

import org.junit.Test;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;

public class MMSeg4jTest {
	@Test
	public void testSegment() {
		try {
			String text = "那个好看的笑容里面全是悲伤，他在行尸走肉的活着，他的故事悲伤的像一场没有结局的黑白电影，他是她小说里的主角， 她懂他，他爱过她，她不知道自己是爱他的的外表，还是爱他的故事，还是爱他身上的那个自己。";
			Segmentor segmentor = new MmsegSegmentor();
			System.out.println(segmentor.seg(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
