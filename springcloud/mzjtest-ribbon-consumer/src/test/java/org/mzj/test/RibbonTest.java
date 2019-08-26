package org.mzj.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.AbstractConfiguration;
import org.junit.Test;

import com.netflix.client.ClientException;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

public class RibbonTest {

	@Test
	public void testBaseLB() {
		// 创建负载均衡器对象
		ILoadBalancer lb = new BaseLoadBalancer();
		
		// 设置服务器列表
		List<Server> servers = new ArrayList<Server>();
		servers.add(new Server("localhost", 8080));
		servers.add(new Server("localhost", 8081));
		// 向负载均衡器中添加服务列表
		lb.addServers(servers);
		// 默认规则：轮询
		for (int i = 0; i < 10; i++) {
			Server s = lb.chooseServer(null);
			System.out.println(s);
		}
	}
	
	//使用自定义负载均衡器
	@Test
	public void testMyRule() {
	    // 创建负载均衡器
	    BaseLoadBalancer blb = new BaseLoadBalancer();
	    // 创建自定义负载均衡器
	    MyRule myRule = new MyRule();
	    
	    // 设置负载均衡器和规则
	    myRule.setLoadBalancer(blb);
	    blb.setRule(myRule);
	    
	    // 设置服务器列表
	    List<Server> servers = new ArrayList<Server>();
	    servers.add(new Server("localhost", 8080));
	    servers.add(new Server("localhost", 8081));
	    blb.setServersList(servers);
		for (int i = 0; i < 10; i++) {
			Server s = blb.chooseServer(null);
			System.out.println(s);
		}
	}
	
	//使用自定义负载均衡器访问服务
	@Test
	public void testMyRule2() {
		try {
			// 写入服务列表
			AbstractConfiguration configManager = ConfigurationManager.getConfigInstance();
			configManager.setProperty("my-client.ribbon.listOfServers", "localhost:8080,localhost:8081");
			// 配置规则类
			configManager.setProperty("my-client.ribbon.NFLoadBalancerRuleClassName", MyRule.class.getName());
			// 输出服务列表
			System.out.println("服务列表：" + configManager.getProperty("my-client.ribbon.listOfServers"));
			// 创建客户端
			RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
			// 创建request对象
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("/mzjtestboot/animal/call")).build();
			// 多次访问测试
			for (int i = 0; i < 10; i++) {
				// 创建response对象
				HttpResponse response = client.executeWithLoadBalancer(request);
				// 接收请求结果
				String json = response.getEntity(String.class);
				// 打印结果
				System.out.println(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
