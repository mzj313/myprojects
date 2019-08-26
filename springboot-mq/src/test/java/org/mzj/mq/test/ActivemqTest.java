package org.mzj.mq.test;

import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActivemqTest {

	@Test
	public void testSendMsg() {
		try {
			String userName = "";
			String password = "";
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, "failover:(tcp://127.0.0.1:61616)");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("testqueue");
			
			MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
            String msg = "这是一条测试消息" + LocalDate.now();
            System.out.println("发送消息：" + msg);
			TextMessage message = session.createTextMessage(msg);
			
            producer.send(message);
            
            session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查看jdbc持久化消息
	@Test
	public void testQueryMsg() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/activemq";
			String user = "root";
			String password = "root123";
			java.sql.Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from activemq_msgs");
			while(rs.next()) {
				System.out.println("container: " + rs.getString("container"));
				System.out.println("msgid_prod: " + rs.getString("msgid_prod"));
				System.out.println("msgid_seq: " + rs.getString("msgid_seq"));
				Blob msg = rs.getBlob("msg");
				System.out.println("msg: " + new String(msg.getBytes(1, (int)msg.length())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
