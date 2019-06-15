package enumeration;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class Reflection {

	enum Explore { HERE, THERE }
	
	public static Set<String> analyze(Class<?> enumClass) {
		System.err.println("--- Analyzing Class: " + enumClass + " ---");
		System.err.println("Interfaces:");
		for (Type type : enumClass.getGenericInterfaces())
			System.err.println(type);
		System.err.println("Base: " + enumClass.getSuperclass());
		System.err.println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for (Method method : enumClass.getMethods())
			methods.add(method.getName());
		System.err.println(methods);
		return methods;
	}
	
	public static void main(String[] args) {
		analyze(Explore.class);
		analyze(Enum.class);
	}
}
