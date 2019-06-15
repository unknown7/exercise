package thread;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.err.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.err.println("Interrupt sent to " + r.getClass().getName());
	}
	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.err.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}
class SleepBlocked implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("InterruptedException");
		}
		System.err.println("Exiting SleepBlocked.run()");
	}
}
class IOBlocked implements Runnable {
	private InputStream is;
	public IOBlocked(InputStream is) {
		this.is = is;
	}
	@Override
	public void run() {
		try {
			System.err.println("Waiting for read()");
			is.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted())
				System.err.println("Interrupted from blocked I/O");
			else
				throw new RuntimeException(e);
		}
		System.err.println("Exiting IOBlocked.run()");
	}
}
class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while (true)
			Thread.yield();
	}
	public SynchronizedBlocked() {
		new Thread() {
			@Override
			public void run() {
				f();
			}
		}.start();
	}
	@Override
	public void run() {
		try {
			System.err.println("Trying to call f()");
			f();
			System.err.println("Exiting SynchronizedBlocked.run()");
		} catch (Exception e) {
			System.err.println("Interrupted from blocked synchronized");
		}
	}
}