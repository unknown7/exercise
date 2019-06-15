package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interrupting2 {
	public static void main(String[] args) throws InterruptedException {
//		Thread t = new Thread(new BlockedMutex2());
//		t.start();
//		TimeUnit.SECONDS.sleep(3);
//		System.err.println("Issuing t.interrupt()");
//		t.interrupt();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new BlockedMutex2());
		TimeUnit.SECONDS.sleep(3);
		System.err.println("Issuing t.interrupt()");
		exec.shutdownNow();
	}
}
class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		lock.lock();
	}
	public void f() {
		try {
//			lock.lock();
			lock.lockInterruptibly();
			System.err.println("lock acquired in f()");
		} catch (Exception e) {
			System.err.println("Interrupted from lock acquisition in f()");
		}
	}
}
class BlockedMutex2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	@Override
	public void run() {
		System.err.println("Waiting fro f() in BlockedMutex");
		blocked.f();
		System.err.println("Broken out of blocked call");
	}
}