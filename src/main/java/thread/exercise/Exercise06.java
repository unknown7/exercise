package thread.exercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import thread.QE;

public class Exercise06 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	private static Random rand = new Random(47);
	@Override
	public void run() {
		int time = rand.nextInt(5);
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.err.println("Thread #" + id + " slept " + time + " seconds.");
		}
	}
	
	public static void main(String[] args) {
		QE.exec(Exercise06.class);
	}
}
