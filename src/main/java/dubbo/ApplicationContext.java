package dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.start();
        synchronized (ApplicationContext.class) {
            while (true) {
                ApplicationContext.class.wait();
            }
        }

    }
}
