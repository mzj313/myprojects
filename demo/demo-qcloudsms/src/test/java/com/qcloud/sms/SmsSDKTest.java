package com.qcloud.sms;

import java.util.ArrayList;

import org.junit.Test;

public class SmsSDKTest {
	//农商行：
	public static final int appid = 1400032536;
	public static final String appkey = "9fce913e0180cd0f2ec362b4927bd89e";
	public static final int tmplId = 22955;//模板ID
	
	String phoneNumber = "18801011954";
	String phoneNumber2 = "12345678902";
	String phoneNumber3 = "12345678903";
	
	@Test
	public void test普通单发() {
		try {
			SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult singleSenderResult = singleSender.send(0, "86", phoneNumber, "您注册的验证码：1234", "", "");
			System.out.println(singleSenderResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test指定模板单发() {
		try {
			SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
	    	 //假设短信模板 id 为 1，模板内容为：测试短信，{1}，{2}，{3}，上学。
	    	ArrayList<String> params = new ArrayList<>();
	    	params.add("2323");
	    	SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", phoneNumber, tmplId, params, "", "", "");
	    	System.out.println(singleSenderResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
