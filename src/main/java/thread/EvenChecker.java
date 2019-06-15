package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator gen;
	private final int id;
	public EvenChecker(IntGenerator gen, int id) {
		this.gen = gen;
		this.id = id;
	}
	@Override
	public void run() {
		while (!gen.isCanceled()) {
			int val = gen.next();
			if (val % 2 != 0) {
				System.err.println(val + " not even!");
				gen.cancel();
			}
		}
	}
	public static void test(IntGenerator gen, int count) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++)
			exec.execute(new EvenChecker(gen, i));
		exec.shutdown();
	}
	public static void test(IntGenerator gen) {
		test(gen, 10);
	}
}
