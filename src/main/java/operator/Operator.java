package operator;

public class Operator {
	public static void main(String[] args) {
		// 二进制111111
		int a = 63;
		
		// 二进制000111
		int b = 7;
		
		System.err.println(a & b); // 000111 --> 7
		System.err.println(a | b); // 111111 --> 63
		System.err.println(a ^ b); // 111000 --> 56
		System.err.println(~a);    // 000000 --> 0
		System.err.println(~b);    // 111000 --> 56
		
		System.err.println(~2);    // 10     --> 
	}
}
