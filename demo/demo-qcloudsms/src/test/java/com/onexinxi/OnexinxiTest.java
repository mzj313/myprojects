package com.onexinxi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OnexinxiTest {
	public void test01() {
		// TODO Auto-generated method stub
		// long l = System.currentTimeMillis(); //当前时间的毫秒
		long l = 1421377731108L; // 当前时间的毫秒
		System.out.println(l);
		Date date = new java.util.Date(l);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));// 打印
																						// 转换为时间格式
	}
}
