package thread;

import java.util.concurrent.TimeUnit;

public class DaemonDontRunFinally {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
		TimeUnit.MILLISECONDS.sleep(101);
	}
}

class ADaemon implements Runnable {
	@Override
	public void run() {
		try {
			System.err.println("Starting ADaemon");
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.err.println("This should always run?");
		}
	}
}