package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise18 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Notify());
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
//		Thread t = new Thread(new Notify());
//		t.start();
//		TimeUnit.SECONDS.sleep(2);
//		t.interrupt();
	}
}
class Sleeper {
	public void f() {
		try {
			System.err.println("sleep...");
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.err.println("wake up");
		}
	}
}
class Notify implements Runnable {
	private Sleeper s = new Sleeper();
	@Override
	public void run() {
		s.f();
	}
}