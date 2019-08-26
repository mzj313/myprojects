package org.mzj.test;

import java.util.List;
import java.util.Random;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/***
 * 自定义负载均衡器
 */
public class MyRule implements IRule {

	private ILoadBalancer lb;// 声明负载均衡器接口

	@Override
	public Server choose(Object key) {
		// 获取服务器列表
		List<Server> servers = lb.getAllServers();
		// 生产随机数
		Random r = new Random();
		int rand = r.nextInt(5);
		if (rand == 1) {
			// 尽量少用8081
			return getServerByPort(servers, 8081);
		} else {
			return getServerByPort(servers, 8080);
		}
	}

	/**
	 * 根据传入的端口号，返回服务对象
	 * 
	 * @param servers
	 * @param port
	 * @return
	 */
	private Server getServerByPort(List<Server> servers, int port) {
		for (Server s : servers) {
			if (s.getPort() == port) {
				return s;
			}
		}
		return null;
	}

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb = lb;
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return this.lb;
	}
}
