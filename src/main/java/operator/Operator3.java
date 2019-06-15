package operator;

public class Operator3 {
	public static void main(String[] args) {
		Integer a1 = 127;
		Integer a2 = 127;
		System.err.println(a1 == a2);

		String[] str = "Hello Hello".split(" ");
		System.err.println(str[0] == str[1]);
	}
}
