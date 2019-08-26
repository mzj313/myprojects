package org.mzj.mq.test;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mzj.mq.Application;
import org.mzj.mq.activemq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class ActivemqSpringTest {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    private Producer producer;

    @Test
    public void 发送消息() {
        this.producer.sendToQueue("Test queue message");
//        Thread.sleep(1000L);
//        assertThat(this.outputCapture.toString().contains("Test queue message")).isTrue();
    }

    @Test
    public void 发送消息到外部服务器() {
    	Destination desc = new ActiveMQQueue("mzjqueue");
    	producer.sendMessage(desc, "沐紫剑测试");  
    }
    
    @Test
    public void 发送消息到外部服务器通过jmsTemplate模板() {
    	producer.sendMsg("沐紫剑测试");  
    }
}
