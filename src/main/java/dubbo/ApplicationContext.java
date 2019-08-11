package dubbo;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class ApplicationContext {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object first = applicationContext.getBean("first");
        System.err.println(first);
    }
}
