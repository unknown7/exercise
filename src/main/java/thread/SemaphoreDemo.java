package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	final static int SIZE = 25;
	public static void main(String[] args) throws InterruptedException {
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++)
			exec.execute(new CheckoutTask<Fat>(pool));
		System.err.println("All CheckoutTasks created");
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < SIZE; i++) {
			Fat f = pool.checkOut();
			System.err.println(i + ": main() thread checked out");
			f.operation();
			list.add(f);
		}
		Future<?> blocked = exec.submit(new Runnable() {
			@Override
			public void run() {
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		System.err.println("Checking in objects in " + list);
		for (Fat fat : list)
			pool.checkIn(fat);
		for (Fat fat : list)
			pool.checkIn(fat);
		exec.shutdown();
	}
}
class Fat {
	private volatile double d;
	private static int counter;
	private final int id = counter++;
	public Fat() {
		for (int i = 0; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}
	public void operation() { System.err.println(this); }
	@Override
	public String toString() { return "Fat id: " + id; }
}
class CheckoutTask<T> implements Runnable {
	private static int counter;
	private final int id = counter++;
	private Pool<T> pool;
	public CheckoutTask(Pool<T> pool) {
		this.pool = pool;
	}
	@Override
	public void run() {
		try {
			T item = pool.checkOut();
			System.err.println(this + " check out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.err.println(this + " check in " + item);
			pool.checkIn(item);
		} catch (Exception e) {
		}
	}
	@Override
	public String toString() {
		return "CheckoutTask " + id;
	}
}