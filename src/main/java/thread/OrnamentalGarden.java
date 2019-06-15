package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrnamentalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Entrance(i));
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			System.err.println("Some task were not terminated!");
		System.err.println("Total: " + Entrance.getTotalCount());
		System.err.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}
class Count {
	private int count = 0;
	private Random random = new Random(47);
	public synchronized int increment() {
		int temp = count;
		if (random.nextBoolean())
			Thread.yield();
		return (count = ++temp);
	}
	public synchronized int value() { return count; }
}
class Entrance implements Runnable {
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;
	public static void cancel() { canceled = true; }
	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}
	private static Lock lock = new ReentrantLock();
	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			lock.lock();
			System.err.println(this + " Total: " + count.increment());
			lock.unlock();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.err.println("Stopping: " + this);
	}
	public synchronized int getValue() { return number; }
	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}
	public static int getTotalCount() {
		return count.value();
	}
	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}