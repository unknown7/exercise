package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Exercise09 implements Runnable {
	private static class MinPriorityFactory implements ThreadFactory {
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setPriority(Thread.MIN_PRIORITY);
			return t;
		}
	}
	private int countDown = 5;
	private volatile double d;
	private int priority;
	public Exercise09() {}
	public Exercise09(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}
	@Override
	public void run() {
		if (priority != 0)
			Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 0; i < 10000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.err.println(this);
			if (--countDown == 0) return;
		}
	}
	
	public static void main(String[] args) {
//		ExecutorService exec = Executors.newCachedThreadPool();
//		for (int i = 0; i < 5; i++)
//			exec.execute(new Exercise09(Thread.MIN_PRIORITY));
//		exec.execute(new Exercise09(Thread.MAX_PRIORITY));
//		exec.shutdown();
		
		System.err.println("================================================");
		
		ExecutorService exec = Executors.newCachedThreadPool(new MinPriorityFactory());
		for (int i = 0; i < 5; i++)
			exec.execute(new Exercise09());
		exec.execute(new Exercise09(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
