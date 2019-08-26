package org.demo.getui;

import org.junit.Test;

import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class GetuiTemplateTest extends GetuiTestBase {

	// 1.点击通知打开应用模板
	// 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用。
	// （激活后，打开应用的首页，如果只要求点击通知唤起应用，不要求到哪个指定页面就可以用此功能。
	// 场景1：针对沉默用户，发送推送消息，点击消息栏的通知可直接激活启动应用，提升应用的转化率。
	@Test
	public void testNotificationTemplate打开应用() {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(1);
		template.setTransmissionContent("请输入您要透传的内容");
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle("请输入通知栏标题");
		style.setText("请输入通知栏内容");
		// 配置通知栏图标
		style.setLogo("icon.png");
		// 配置通知栏网络图标
		style.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);

		push(appId, appKey, template);
	}

	// 2. 点击通知打开网页模板
	// 在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页。
	// 场景1：推送广促销活动，用户点击通知栏信息，直接打开到指定的促销活动页面，推送直接到达指定页面，免去了中间过程的用户流失。
	@Test
	public void testLinkTemplate打开网页() {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle("请输入通知栏标题");
		style.setText("请输入通知栏内容");
		// 配置通知栏图标
		style.setLogo("icon.png");
		// 配置通知栏网络图标
		style.setLogoUrl("http://img.bss.csdn.net/201705151437139385.jpg");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);

		// 设置打开的网址地址
		template.setUrl("http://www.getui.com");
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

		push(appId, appKey, template);
	}

	// 3. 点击通知弹窗下载模板
	// 消息以弹框的形式展现，点击弹框内容可启动下载任务。
	// 场景1：应用有更新，点击推送的更新通知，弹出下载弹窗，点击可启动应用更新下载。
	@Test
	public void testNotyPopLoadTemplate弹窗下载() {
		NotyPopLoadTemplate template = new NotyPopLoadTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle("请输入通知栏标题");
		style.setText("请输入通知栏内容");
		// 配置通知栏图标
		style.setLogo("icon.png");
		// 配置通知栏网络图标
		style.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);

		// 设置弹框标题与内容
		template.setPopTitle("弹框标题");
		template.setPopContent("弹框内容");
		// 设置弹框显示的图片
		template.setPopImage("");
		template.setPopButton1("下载");
		template.setPopButton2("取消");
		// 设置下载标题
		template.setLoadTitle("下载标题");
		template.setLoadIcon("file://icon.png");
		// 设置下载地址
		template.setLoadUrl("http://gdown.baidu.com/data/wisegame/80bab73f82cc29bf/shoujibaidu_16788496.apk");
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

		push(appId, appKey, template);
	}

	// 4. 透传消息模版
	// 透传消息：是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。
	// 客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。iOS推送也使用该模板。
	// 场景1：自定义通知栏样式不想使用默认的通知栏样式，即可使用消息透传的形式，自定义通知栏展现形式，使发送的通知更醒目，更突出。
	// 场景2：自定义通知到达之后的动作希望用户点击通知后启动应用直接到和通知相关的界面，免去中间跳转的流失。如用户预订更新的某本图书有更新，点击通知直接启动应用到对应图书的页面，免去用户打开应用后的查找，节省中间环节，提高转化。
	// 场景3：仅传递信息，不做任何展示推送一串代码给应用，该代码仅此app可以解析。收到透传消息时，界面不作任何展示，用户无感知，应用收到命令后按代码执行操作。
	public void testTransmissionTemplate透传消息() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent("请输入需要透传的内容");
	    
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    
	    push(appId, appKey, template);
	}
	
	@Test
	public void testTransmissionTemplate透传消息4IOS() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent("透传内容");
	    template.setTransmissionType(2);
	    
	    //iOS推送需要在代码中通过TransmissionTemplate的setAPNInfo接口设置相应的APNs通知参数。 透传模板传输的数据最大为是2KB，APNs传输数据最大支持2KB。
	    APNPayload payload = new APNPayload();
	    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
	    payload.setAutoBadge("+1");
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");

	    //简单模式APNPayload.SimpleMsg 
	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

	    //字典模式使用APNPayload.DictionaryAlertMsg
	    //payload.setAlertMsg(getDictionaryAlertMsg());

	    // 添加多媒体资源
	    payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
	                .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
	                .setOnlyWifi(true));

	    template.setAPNInfo(payload);
	    
	    push(appId, appKey, template);
	}
	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody("body");
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // iOS8.2以上版本支持
	    alertMsg.setTitle("Title");
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}

	@Test
	public void test单推() {
		StringBuffer sb = new StringBuffer("{");
		sb.append("\"title\":\"测试\",");
		sb.append("\"contentType\":1,");
		sb.append("\"contentid\":\"84d8485a1bf64c0cb07c7ba6667677ae\",");
		sb.append("\"url\":\"http://wap.wxgc.wxrb.com/pages/h5/jk/84d8485a1bf64c0cb07c7ba6667677ae.html\",");
		//for IOS
		StringBuffer aps = new StringBuffer("{");
		aps.append("\"alert\":\"来自服务端的消息\",");
		aps.append("\"badge\":1");
		aps.append("}");
		sb.append("\"aps\":" + aps.toString());
		sb.append("}");
		String content = sb.toString();
		System.out.println(content);
		String title = "测试";
		ITemplate template = getTemplate(content, title);
		pushSingle(appId, appKey, template , CID);
	}
}
