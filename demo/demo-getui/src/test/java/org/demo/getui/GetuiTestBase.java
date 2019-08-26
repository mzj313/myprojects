package org.demo.getui;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GetuiTestBase {
	// 定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
	//188
//	protected static String appId = "G3Bu2i9UUn7rti8krF9fV5", appKey = "NBEZejzjWDAkTUGvvLZrJ2", masterSecret = "1mIzM7UP9ZABoSl5QoLym6";
	protected static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	protected static String CID = "c9bc54921dbe24ea4a60b2b21a282d56";//clientid，单推使用，安装demo后能拿到
	
	//无锡
	protected static String appId = "q6LdsGw4i98xyVu2PUsVO2", appKey = "JIZgJKdAql51zymf3yWia3", masterSecret = "xTRwnNa3op8vVoWJDDWEr3";

	/**
	 * 群推
	 * @param appId
	 * @param appKey
	 * @param template
	 */
	public static void push(String appId, String appKey, ITemplate template) {
		IGtPush push = new IGtPush(url, appKey, masterSecret);

		// 定义"AppMessage"类型消息对象
		AppMessage message = new AppMessage();
		//消息内容模板
		message.setData(template);
		
		//发送的目标App列表
		List<String> appIds = new ArrayList<String>();
		appIds.add(appId);
		message.setAppIdList(appIds);
		
		//是否支持离线发送
		message.setOffline(true);
		//离线消息有效期(单位毫秒)
		message.setOfflineExpireTime(1000 * 600);

		IPushResult ret = push.pushMessageToApp(message);
		System.out.println(ret.getResponse().toString());
	}
	
	public static void pushSingle(String appId, String appKey, ITemplate template, String CID) {
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		
		SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
	}
	
	public TransmissionTemplate getTemplate(String content, String title) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(content);//2048中/英字符
		
		//for IOS
		{
			APNPayload payload = new APNPayload();
		    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
		    payload.setAutoBadge("+1");
		    payload.setContentAvailable(1);
		    payload.setSound("default");
		    payload.setCategory("$由客户端定义");//在客户端通知栏触发特定的action和button显示
		    payload.addCustomMsg("content", content);

		    //简单模式APNPayload.SimpleMsg
		    payload.setAlertMsg(new APNPayload.SimpleAlertMsg(title));//通知消息体

		    //字典模式使用APNPayload.DictionaryAlertMsg
		    //payload.setAlertMsg(getDictionaryAlertMsg());

		    // 添加多媒体资源
//			payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
//				   .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
//				   .setOnlyWifi(true));

		    template.setAPNInfo(payload);//最大2k
		}
		
		return template;
	}
}
