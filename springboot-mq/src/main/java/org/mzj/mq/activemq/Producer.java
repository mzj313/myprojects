package org.mzj.mq.activemq;

import javax.jms.Destination;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate; //springboot提供

	@Autowired
	@Qualifier("simpleQueue")
	private Queue queue;//自带的服务器队列
	// 发送消息到自带的服务器队列
	public void sendToQueue(String msg) {
		System.out.println("======发送消息：" + msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}

	/**
	 * 发送到外部的服务器队列
	 * @param destination
	 * @param message
	 */
	public void sendMessage(Destination destination, final String message) {
		this.jmsMessagingTemplate.convertAndSend(destination, message);
	}
	
	// ============= 不走springboot，spring定制的模板   =============
	@Autowired
	private JmsTemplate jmsTemplate;//在config里配置
	// 利用在config里注解的模板发送消息
	public void sendMsg(String message) {
		System.out.println("======利用 " + jmsTemplate + " 发送消息：" + message);
		jmsTemplate.convertAndSend(message);
	}
}
