package thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	private void pause() throws InterruptedException {
		if (ponderFactor == 0) return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}
	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.err.println(this + " thinking");
				pause();
				System.err.println(this + " grabbing right");
				right.take();
				System.err.println(this + " grabbing left");
				left.take();
				System.err.println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (Exception e) {
			System.err.println(this + " exiting via interrupt");
		}
	}
	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
