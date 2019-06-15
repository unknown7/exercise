package thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC2 implements Runnable {
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	private String id;
	private boolean active = false;
	private ABC2 next;
	public ABC2(String id, ABC2 next) {
		this.id = id;
		this.next = next;
	}
	public ABC2(String id, boolean active, ABC2 next) {
		this.id = id;
		this.active = active;
		this.next = next;
	}
	@Override
	public void run() {
		lock.lock();
		try {
			while (!Thread.interrupted()) {
				while (!active)
					condition.await();
				TimeUnit.MILLISECONDS.sleep(500);
				System.err.print(id);
				active = false;
				next.active = true;
				condition.signalAll();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
		System.err.println(id + " interrupted.");
	}
	public static void main(String[] args) throws IOException {
		ABC2 c = new ABC2("C", null);
		ABC2 b = new ABC2("B", c);
		ABC2 a = new ABC2("A", true, b);
		c.next = a;
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(a);
		exec.execute(b);
		exec.execute(c);
		System.in.read();
		exec.shutdownNow();
	}
}
