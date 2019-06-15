package thread;

import java.util.concurrent.TimeUnit;

public class InterruptingIdiom {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(1001);
		t.interrupt();
	}
}
class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int id) {
		this.id = id;
		System.err.println("NeedsCleanup " + id);
	}
	public void cleanup() {
		System.err.println("Cleaning up " + id);
	}
}
class Blocked3 implements Runnable {
	private volatile double d = 0.0;
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.err.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.err.println("Calculating");
						for (int i = 1; i < 250000000; i++)
							d += (Math.PI + Math.E) / d;
						System.err.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.err.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.err.println("Exiting via InterruptedException");
		}
	}
}