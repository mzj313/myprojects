package org.mzj.mq.activemq;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ActivemqConfig  {
	// 本服务器做消息服务器的一个队列
    @Bean
    public Queue simpleQueue() {
        return new ActiveMQQueue("myqueue");
    }
    
    // ============= 不走springboot，spring定制的模板   =============
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	private static final String ORDER_QUEUE = "testqueue";
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activemqConnectionFactory = new ActiveMQConnectionFactory();
		activemqConnectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		SingleConnectionFactory connectionFactory = new SingleConnectionFactory(activemqConnectionFactory);
		connectionFactory.setReconnectOnException(true);// 可以自动重连重启后的消息服务器
		return connectionFactory;
	}
	
	@Bean 
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_QUEUE);
		template.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化
		System.out.println("config里构造的JmsTemplate: " + template);
		return template;
	}
}
