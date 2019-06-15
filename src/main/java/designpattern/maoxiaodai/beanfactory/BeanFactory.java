package designpattern.maoxiaodai.beanfactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {
	private static Map<String, Object> map = new HashMap<String, Object>();
	private BeanFactory() {
	}
	public static void init() {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read("src/test/resource/bean.xml");
			Element root = document.getRootElement();
			List<?> beans = root.elements();
			for (Object b : beans) {
				Element bean = (Element) b;
				String id = bean.attributeValue("id");
				String _class = bean.attributeValue("class");
				map.put(
					id,
					Class.forName(_class).newInstance()
				);
				List<?> properties = bean.elements();
				for (Object p : properties) {
					Element property = (Element) p;
					String name = property.attributeValue("name");
					String ref = property.attributeValue("ref");
					String setterName = generateSetterName(name);
					Class<?> clazz = Class.forName(_class);
					Method method = clazz.getDeclaredMethod(setterName, map.get(ref).getClass().getInterfaces()[0]);
					method.invoke(map.get(id), map.get(ref));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Object getBean(String beanName) {
		return map.get(beanName);
	}
	
	private static String generateSetterName(String property) {
		StringBuilder sb = new StringBuilder();
		sb.append("set")
		.append(property.substring(0, 1).toUpperCase())
		.append(property.substring(1, property.length()));
		return sb.toString();
	}
}
