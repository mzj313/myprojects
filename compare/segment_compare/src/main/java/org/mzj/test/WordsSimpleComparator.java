package org.mzj.test;

import java.util.ArrayList;
import java.util.List;

public class WordsSimpleComparator extends WordsComparator{

	public int doCompare(List<Word> words1, List<Word> words2) {
		if(!(words1 instanceof ArrayList && words2 instanceof ArrayList)) {
			throw new RuntimeException("此比较器只支持ArrayList");
		}
		ArrayList<Word> l1,l2;//确保l1集合大
		if(words1.size() >= words2.size()) {
			l1 = (ArrayList<Word>) ((ArrayList<Word>)words1).clone(); l2 =  (ArrayList<Word>) ((ArrayList<Word>)words2).clone();
		} else {
			l1 = (ArrayList<Word>) ((ArrayList<Word>)words2).clone(); l2 =  (ArrayList<Word>) ((ArrayList<Word>)words1).clone();
		}
		
		return recurCompare(l1, l2, "0");
	}
	
	// 递归调用
	public int recurCompare(ArrayList<Word> l1, ArrayList<Word> l2, String depth) {
		int size1 = l1.size(), size2 = l2.size();
		if(bLog) {
			for (int i = 0; i < 20 - depth.length(); i++) {
				System.out.print("---");
			}
			System.out.println("depth=" + depth + " size1=" + size1);
			System.out.println("l1= " + l1);
			System.out.println("l2= " + l2);
		}
		if(l2.isEmpty()) return 0;
		if(size1 == 1) return calc(l1.get(0), l2.get(0));
		int width = size1 - size2;
		int oriSimi = calc(l1,l2);//原始相似度
		int maxSimi = oriSimi;
		ArrayList<Word> oril2 = (ArrayList<Word>) l2.clone();
		int maxTime = 0;//移动到最接近时的次数
		ArrayList<Word> maxl2;
		int[] results = new int[width];
		for(int i = 0; i < width; i++) {//移动窗口
			l2.add(0, new Word());
			results[i] = calc(l1,l2);
			if(results[i] > maxSimi) {
				maxSimi = results[i];
				maxl2 = (ArrayList<Word>) l2.clone();
				maxTime = i;
			}
		}
		if(bLog) System.out.println("maxTime=" + maxTime + " maxSimi=" + maxSimi);
		// 考虑移动窗口的三种情况
		// [----------|----------]     [----------|----------]     [----------|----------]
		//    [----]                           [--|--]                           [----]
		int middle = size1/2;
		ArrayList<Word> l1left = new ArrayList<Word>(l1.subList(0, middle));
		int orimiddle2 = middle < maxTime ? 0 : middle-maxTime;
		int simi1 = 0;
		if(orimiddle2 > 0) {
			int toIdx2 = oril2.size() > orimiddle2 ? orimiddle2 : oril2.size();
			ArrayList<Word> l2left = new ArrayList<Word>(oril2.subList(0, toIdx2));
			simi1 = recurCompare(l1left, l2left, depth+"-l");//
		}
		int simi2 = 0;
		if(oril2.size() > orimiddle2) {
			ArrayList<Word> l1right = new ArrayList<Word>(l1.subList(middle, size1));
			ArrayList<Word> l2right = new ArrayList<Word>(oril2.subList(orimiddle2, size2));
			simi2 = recurCompare(l1right, l2right, depth+"-r");//
		}
		int sumSimi = simi1 + simi2;
		return Math.max(sumSimi, maxSimi);
	}
	
	
	public int calc(Word w1, Word w2) {
		if (w1.value.equals(w2.value)) {
			return w1.weight + w2.weight;
		} else {
			return 0;
		}
	}
	
}
