package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadControl {
	public static void main(String[] args) throws Exception {
		Worker a = new Worker(true, "A");
		Worker b = new Worker(false, "B");
		Worker c = new Worker(false, "C", a);
		a.next = b;
		b.next = c;
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(a);
		exec.execute(b);
		exec.execute(c);
		System.in.read();
		exec.shutdownNow();
	}
}
class Worker implements Runnable {
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	boolean active = false;
	String content;
	Worker next;
	Worker(boolean active, String content) {
		this(active, content, null);
	}
	Worker(boolean active, String content, Worker next) {
		this.active = active;
		this.content = content;
		this.next = next;
	}
	@Override
	public void run() {
		lock.lock();
		try {
			while (!Thread.interrupted()) {
				while (!active)
					condition.await();
				System.err.print(content);
				TimeUnit.MILLISECONDS.sleep(500);
				active = false;
				next.active = true;
				condition.signalAll();
			}
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}
}