package dubbo;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class ApplicationContext {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Consumer consumer = (Consumer) applicationContext.getBean("consumer");
//        System.err.println(consumer.getUser());
        applicationContext.start();
        synchronized (ApplicationContext.class) {
            while (true) {
                ApplicationContext.class.wait();
            }
        }

    }
}
