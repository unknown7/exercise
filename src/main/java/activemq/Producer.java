package activemq;

import java.util.concurrent.TimeUnit;

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

public class Producer {
	private static ConnectionFactory factory;
	private static Connection conn;
	private static Session session;
	private static Destination des;
	private static MessageProducer producer;
	private static Session sessionRec;
	private static Destination rec;
	private static MessageConsumer consumer;
	static {
		try {
			factory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,
					ActiveMQConnection.DEFAULT_BROKER_URL);
			conn = factory.createConnection();
			conn.start();
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			des = session.createTopic("MyFirstTopic");
			producer = session.createProducer(des);
			
			sessionRec = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			rec = sessionRec.createQueue("confirm");
			consumer = sessionRec.createConsumer(rec);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {
						System.err.println(((TextMessage) message).getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws JMSException, InterruptedException {
		for (int i = 0; i < 20; i++) {
			TimeUnit.SECONDS.sleep(3);
			TextMessage message = session.createTextMessage();
			message.setText("Hello" + i);
			producer.send(message);
			session.commit();
		}
	}
}
