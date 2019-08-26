package org.mzj.mq.activemq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	// 可以使用SessionAwareMessageListener以及MessageListenerAdapter来实现消息驱动POJO
	// 参考JmsListenerAnnotationBeanPostProcessor
    @JmsListener(destination = "myqueue")
    public void receiveQueue(String text) {
        System.out.println("======收到消息：" + text);
    }
    
    // 监听外部消息服务器的消息
    @JmsListener(destination = "mzjqueue")
    public void receiveOutQueue(String text) {
        System.out.println("======收到外部消息：" + text);
    }
    
    // ============= 不走springboot，spring定制的模板   =============
 	@Autowired
 	private JmsTemplate jmsTemplate;//在config里配置
// 	@PostConstruct
 	public void init() {
 		receiveMsg();
 	}
 	// 不用@JmsListener，自己实现监听
 	public void receiveMsg() {
 		ExecutorService executor = Executors.newCachedThreadPool();
 		executor.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						String defaultDestination = jmsTemplate.getDefaultDestinationName();
						System.out.println("======监听" + defaultDestination + "消息...");
						Object message = jmsTemplate.receiveAndConvert();
						System.out.println("======收到消息：" + message);
					} catch (JmsException e) {
						System.err.println("======监听消息异常 " + e.getMessage());
						try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e1) {}
					}
				}
			}
		});
 	}
}
