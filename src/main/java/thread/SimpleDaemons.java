package thread;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.err.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.err.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(200);
	}
}
