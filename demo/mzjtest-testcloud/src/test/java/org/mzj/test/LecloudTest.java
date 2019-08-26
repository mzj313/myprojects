package org.mzj.test;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

/**
 * 乐视云接口测试
 * 
 * @author lijw
 * @date 2016年11月30日
 * @version V1.0
 */
public class LecloudTest {
	private static final String _URI = "http://api.open.lecloud.com/live/execute";
	private static final int _USERID = 878418;
	private static final String _SECRETKEY = "a86ea82595ed546ee9b6fbb39899093d";

	@Test
	public void test活动查询() {
		try {
			String uri = _URI;
			
			Map<String, Object> params = new HashMap<String, Object>();
			//公共参数
			params.put("method", "lecloud.cloudlive.activity.search");
			params.put("timestamp", System.currentTimeMillis());
			params.put("userid", _USERID);
			params.put("ver", "4.1");
			//sign
			String secretkey = _SECRETKEY;
			String sign = getSign(params, secretkey);
			params.put("sign", sign);
			
			byte[] responseBody = executeGet(uri, params);
			
			System.out.println(new String(responseBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test获取录制视频信息() {
		try {
			String uri = _URI;
			
			Map<String, Object> params = new HashMap<String, Object>();
			//公共参数
			params.put("method", "lecloud.cloudlive.activity.getPlayInfo");
			params.put("timestamp", System.currentTimeMillis());
			params.put("userid", _USERID);
			params.put("ver", "4.0");
			//系统参数
			params.put("activityId", "A2016113000002xb");
			
			//sign
			String secretkey = _SECRETKEY;
			String sign = getSign(params, secretkey);
			params.put("sign", sign);
			
			byte[] responseBody = executeGet(uri, params);
			
			System.out.println(new String(responseBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行get方法
	 * @param uri
	 * @param params
	 * @return responseBody
	 * @throws IOException
	 */
	protected byte[] executeGet(String uri, Map<String, Object> params) throws IOException {
		HttpClient client = new HttpClient();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0) {
				uri += "?" + key + "=" + params.get(key);
				i=1;
			}else{
				uri += "&" + key + "=" + params.get(key);
			}
		}
		System.out.println(uri);
		GetMethod method = new GetMethod(uri);
		method.getParams().setContentCharset("UTF-8");
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("method failed" + method.getStatusLine());
		}
		byte[] responseBody = method.getResponseBody();
		return responseBody;
	}
	
	@Test
	public void testGetSign() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("method", "lecloud.cloudlive.activity.create");
		params.put("ver", "4.0");
		params.put("userid", 300100);
		params.put("timestamp", "1470412800000");
		
		params.put("activityName", "testName");
		params.put("activityCategory", "001");
		params.put("startTimeTS", "1470585600000");
		params.put("endTimeTS", "1470758400000");
		params.put("playMode", "0");
		params.put("liveNum", "2");
		params.put("codeRateTypes", "16,19");

		String secretkey = "900150983cd24fb0d6963f7d28e17f72";
		String sign = getSign(params, secretkey);
		System.out.println(sign);
	}

	protected static String getSign(Map<String, Object> params, String secretkey) {
		// key排序
		Set<String> keySet = params.keySet();
		List<String> paramkeys = new ArrayList<String>(keySet);
		Collections.sort(paramkeys);
		// 拼接key,value
		StringBuffer signBuf = new StringBuffer();
		for (String key : paramkeys) {
			signBuf.append(key).append(params.get(key));
		}
		// 拼接secretkey
		signBuf.append(secretkey);
		// md5
		String signStr = signBuf.toString();
		System.out.println("步骤3结果:\n" + signStr);
		String sign = MD5(signStr);
		return sign;
	}

	public final static String MD5(String s) {
		// 注意大小写
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
	}
}
