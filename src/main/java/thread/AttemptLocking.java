package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private Lock lock = new ReentrantLock();
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.err.println("tryLock():" + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(7, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.err.println("tryLock(2, TImeUnit.SECONDS):" + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread() {
			{ setDaemon(true); }
			@Override
			public void run() {
				al.lock.lock();
				System.err.println("acquired");
			}
		}.start();
//		Thread.yield();
		TimeUnit.SECONDS.sleep(1);
		al.untimed();
		al.timed();
	}
}
