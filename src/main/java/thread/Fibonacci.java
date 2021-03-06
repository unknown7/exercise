package thread;

public class Fibonacci implements Generator<Integer> {
	private int count = 0;
	public int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	@Override
	public Integer next() {
		return fib(count++);
	}
	public static void main(String[] args) {
		Fibonacci gen = new Fibonacci();
		for (int i = 0; i < 18; i++) {
			System.err.print(gen.next());
			if (i != 17)
				System.err.print(", ");
		}
	}
}
