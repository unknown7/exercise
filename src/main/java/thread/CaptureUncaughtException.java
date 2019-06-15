package thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.err.println(t.getUncaughtExceptionHandler());
		
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
//		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//			@Override
//			public void uncaughtException(Thread t, Throwable e) {
//				System.err.println("Thread: " + t + " throws an exception, caught.");
//			}
//		});
//		ExecutorService exec = Executors.newCachedThreadPool();
		ExecutorService exec = Executors.newCachedThreadPool(new UncaughtExceptionHandlerFactory());
		exec.execute(new CaptureUncaughtException());
		exec.shutdown();
	}
}

class UncaughtExceptionHandlerFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("Thread: " + t + " throws an exception, caught.");
			}
		};
		t.setUncaughtExceptionHandler(handler);
		System.err.println(t.getUncaughtExceptionHandler());
		return t;
	}
}