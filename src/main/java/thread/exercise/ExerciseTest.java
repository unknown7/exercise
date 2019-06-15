package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExerciseTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
class CounterTest {
	private int count;
	public synchronized int increment() {
		return ++count;
	}
	public synchronized int count() { return count; };
}
class Task implements Runnable {
	private static int count;
	private static CounterTest counter = new CounterTest();
	private final int id = count++;
	@Override
	public void run() {
		try {
			while (true) {
				System.err.println(this + " : " + counter.increment());
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.err.println("Interrupted from " + this);
		}
	}
	@Override
	public String toString() {
		return "Task#" + id;
	}
}