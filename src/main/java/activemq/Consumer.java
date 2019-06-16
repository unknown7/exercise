package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private static ConnectionFactory factory;
	private static Connection conn;
	private static Session session;
	private static Destination des;
	private static MessageConsumer consumer;
	
	private static Session sessionPro;
	private static Destination pro;
	private static MessageProducer producer;
	static {
		try {
			factory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,
					ActiveMQConnection.DEFAULT_BROKER_URL);
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			des = session.createTopic("MyFirstTopic");
			consumer = session.createConsumer(des);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					try {
						System.err.println(((TextMessage) message).getText());
						TextMessage confirm = session.createTextMessage();
						confirm.setText(((TextMessage) message).getText() + "已收到");
						producer.send(confirm);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
			sessionPro = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			pro = sessionPro.createQueue("confirm");
			producer = sessionPro.createProducer(pro);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws JMSException {
	}
}
