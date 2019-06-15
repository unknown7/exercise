package test;

public class TryFinallyTest {
	public static void main(String[] args) {
		System.err.println(test());
	}
	
	static int test() {
		try {
			System.err.println("begin");
			return 1;
		} finally {
			System.err.println("finally");
		}
	}
}
