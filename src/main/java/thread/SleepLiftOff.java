package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepLiftOff extends LiftOff {
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.err.print(status());
//			Thread.sleep(100);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new SleepLiftOff());
		exec.shutdown();
	}
}
