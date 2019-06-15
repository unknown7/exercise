package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace2 {
	static class Horse implements Runnable {
		private static int counter;
		private final int id = counter++;
		private int strides;
		private CyclicBarrier barrier;
		private static Random rand = new Random(47);
		public Horse(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		public String tracks() {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < strides; i++)
				s.append("*");
			s.append(id);
			return s.toString();
		}
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {
					strides += rand.nextInt(3);
					barrier.await();
				}
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		@Override
		public String toString() {
			return "Horse " + id;
		}
	}
	private static final int LENGTH = 75;
	private static final int HORSE_SIZE = 5;
	private static List<Horse> horses = new ArrayList<Horse>();
	private static ExecutorService exec = Executors.newCachedThreadPool();
	public HorseRace2() {
		CyclicBarrier barrier = new CyclicBarrier(HORSE_SIZE, new Runnable() {
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < LENGTH; i++)
					s.append("=");
				System.err.println(s);
				for (Horse horse : horses)
					System.err.println(horse.tracks());
				for (Horse horse : horses)
					if (horse.strides >= LENGTH) {
						System.err.println(horse + " won!");
						exec.shutdownNow();
						return;
					}
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {
					System.err.println("race end");
				}
			}
		});
		for (int i = 0; i < HORSE_SIZE; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	public static void main(String[] args) {
		new HorseRace2();
	}
}
