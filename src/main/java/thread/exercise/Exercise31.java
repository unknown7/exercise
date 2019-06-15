package thread.exercise;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import thread.Chopstick;

public class Exercise31 {
	public static void main(String[] args) throws InterruptedException, IOException {
		int ponder = 5;
		int size = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		ChopstickQueue queue = new ChopstickQueue();
		for (int i = 0; i < size; i++)
			queue.put(new Chopstick());
		for (int i = 0; i < size; i++)
			exec.execute(new Philosopher(queue, i, ponder));
		System.in.read();
		exec.shutdownNow();
	}
}
class ChopstickQueue extends LinkedBlockingQueue<Chopstick> {
	private static final long serialVersionUID = 1L;
	public synchronized Chopstick[] takeTow() throws InterruptedException {
		Chopstick[] chopsticks = new Chopstick[2];
		chopsticks[0] = take();
		chopsticks[1] = take();
		return chopsticks;
	}
	public void put(Chopstick chopstick1, Chopstick chopstick2) throws InterruptedException {
		put(chopstick1);
		put(chopstick2);
	}
}
class Philosopher implements Runnable {
	private ChopstickQueue queue;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	public Philosopher(ChopstickQueue queue, int id, int ponderFactor) {
		this.queue = queue;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}
	private void pause() throws InterruptedException {
		if (ponderFactor == 0) return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.err.println(this + " thinking");
				pause();
				Chopstick[] chopsticks = queue.takeTow();
				System.err.println(this + " grabbing right");
				System.err.println(this + " grabbing left");
				System.err.println(this + " eating");
				pause();
				queue.put(chopsticks[0], chopsticks[1]);
				System.err.println(this + " dropping right");
				System.err.println(this + " dropping left");
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