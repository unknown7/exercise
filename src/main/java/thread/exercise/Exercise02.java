package thread.exercise;

public class Exercise02 implements Runnable {
	private int count = 0;
	public Exercise02(int n) {
		this.count = n;
	}
	public int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			System.err.print(fib(i));
			if (i != count - 1)
				System.err.print(", ");
		}
		System.err.println();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(new Exercise02(7)).start();
	}
}
