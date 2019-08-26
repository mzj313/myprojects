package org.mzj.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Test;

/**
 * 腾讯云直播
 * 
 * @author lijw
 * @date 2016年12月1日
 * @version V1.0
 */
public class QcloudTest {
	/**
	 * 直播接口地址
	 */
	private static final String _URI_ZB = "http://fcgi.video.qcloud.com/common_access";

	@Test
	public void test查询直播状态() {
		try {
			String uri = _URI_ZB;

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmd", "1252926540");// appid
			params.put("interface", "Live_Channel_GetStatus");// 接口名称
			params.put("Param.s.channel_id", "5473_bea08e4bc9");// 直播码
			long t = System.currentTimeMillis();
			params.put("t", t);
			// sign = MD5(key + t)
			String key = "b6a9edaf15c68b2251c58d4f253d459a";// api鉴权key
			String sign = getSign(key, t);
			params.put("sign", sign);

			byte[] responseBody = executeGet(uri, params);

			System.out.println(new String(responseBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test查询录制文件() {
		try {
			String uri = _URI_ZB;
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmd", "1252926540");// appid
			params.put("interface", "Live_Tape_GetFilelist");// 接口名称
			params.put("Param.s.channel_id", "5473_bea08e4bc9");// 直播码
			long t = System.currentTimeMillis();
			params.put("t", t);
			// sign = MD5(key + t)
			String key = "b6a9edaf15c68b2251c58d4f253d459a";// api鉴权key
			String sign = getSign(key, t);
			params.put("sign", sign);
			
			byte[] responseBody = executeGet(uri, params);
			
			System.out.println(new String(responseBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行get方法
	 * 
	 * @param uri
	 * @param params
	 * @return responseBody
	 * @throws IOException
	 */
	protected byte[] executeGet(String uri, Map<String, Object> params) throws IOException {
		HttpClient client = new HttpClient();
		uri = jointUri(uri, params);
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

	protected static String jointUri(String uri, Map<String, Object> params) {
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0) {
				uri += "?" + key + "=" + params.get(key);
				i = 1;
			} else {
				uri += "&" + key + "=" + params.get(key);
			}
		}
		return uri;
	}

	protected static String getSign(String key, long t) {
		// sign = MD5(key + t)
		String sign = MD5(key + t);
		return sign;
	}

	protected final static String MD5(String s) {
		// 注意大小写
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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
	
	//------------------点播-------------------
	/**
	 * 点播接口地址
	 */
	private static final String _URI_DB = "https://vod.api.qcloud.com/v2/index.php";
	@Test
	public void test获取视频详细信息() {
		try {
			String uri = _URI_DB;
			
			Map<String, Object> params = new HashMap<String, Object>();
			//公共参数
			params.put("Action", "DescribeVodPlayUrls");
			params.put("Region", "bj");
			params.put("Timestamp", System.currentTimeMillis());
			params.put("Nonce", 259869386);//随机正整数，与 Timestamp 联合起来, 用于防止重放攻击
			params.put("SecretId", "AKIDvkNo7ENUt67xa1SKEwSqV1LWVHqDqM8i");
			String secretkey = "b2JOG1aw63ta8UdaM1WvBFVfdPyLVUHs";
			String Signature = getSign(params, secretkey );//
			params.put("Signature", Signature);
			params.put("fileId", "9896587163627218158");
			
			byte[] responseBody = executeGet(uri, params);
			
			System.out.println(new String(responseBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String getSign(Map<String, Object> params, String secretkey) {
		// 2.1. 对参数排序
		Set<String> keySet = params.keySet();
		List<String> paramkeys = new ArrayList<String>(keySet);
		Collections.sort(paramkeys);
		// 2.2. 拼接请求字符串 key=value
		StringBuffer signBuf = new StringBuffer();
		for (String key : paramkeys) {
			signBuf.append(key).append("=").append(params.get(key).toString().replaceAll("_", ".")).append("&");
		}
		signBuf.deleteCharAt(signBuf.length()-1);
		// 2.3. 拼接签名原文字符串
		try {
			HttpClient client = new HttpClient();
			String uri = "https://cvm.api.qcloud.com/v2/index.php";
			uri = jointUri(uri, params);
			GetMethod method = new GetMethod(uri);
			method.getParams().setContentCharset("UTF-8");
			int statusCode = client.executeMethod(method);
			if(statusCode == HttpStatus.SC_OK) {
				byte[] data = method.getResponseBody();
				// 2.4. 生成签名串
				byte[] signBytes = getBase64Sha1(new String(data), secretkey);
				//3. 签名串编码
				String sign = URLEncoder.encode(new String(signBytes), "UTF-8");
				return sign;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@Test
	public void testHmacsha1() {
		try {
			String data = "GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0";
			String key = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
			byte[] bases = getBase64Sha1(data, key);
			System.out.println(new String(bases));
			Assert.assertEquals("NSI3UqqD99b/UJb4tbG/xZpRW64=", new String(bases));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] getBase64Sha1(String data, String key) throws Exception {
		Encoder encoder = Base64.getEncoder();
		byte[] bases = encoder.encode(getSha1(data, key));
		return bases;
	}
	
	
	/**   
     * 生成签名数据   
     *    
     * @param data 待加密的数据   
     * @param key  加密使用的key   
     * @throws InvalidKeyException,NoSuchAlgorithmException   
     */    
	private byte[] getSha1(String data, String key) throws Exception {
		String HMAC_SHA1 = "HmacSHA1";
		// 还原密钥 
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1);
		// 实例化Mac
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		//执行消息摘要
		byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		return rawHmac;
	}
	
}
