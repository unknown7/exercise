package operator;

public class Operator2 {
	public static void main(String[] args) {
		String name = Operator2.class.getName();
		name = name.replaceAll(".", "/");
		System.err.println(name);
	}
}
