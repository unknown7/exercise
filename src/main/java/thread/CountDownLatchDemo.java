package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
	static final int SIZE = 100;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++)
			exec.execute(new WaitingTask(latch));
		for (int i = 0; i < SIZE; i++)
			exec.execute(new TaskPortion(latch));
		System.err.println("Launched all tasks");
		exec.shutdown();
	}
}
class TaskPortion implements Runnable {
	private static int counter;
	private final int id = counter++;
	private CountDownLatch latch;
	private Random rand = new Random(47);
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.err.println(this + " completed");
	}
	@Override
	public String toString() {
		return String.format("%1$-3d ", id);
	}
}
class WaitingTask implements Runnable {
	private static int counter;
	private final int id = counter++;
	private CountDownLatch latch;
	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			latch.await();
			System.err.println("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return String.format("WaitingTask %1$-3d ", id);
	}
}