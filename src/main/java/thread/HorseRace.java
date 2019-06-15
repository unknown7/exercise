package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	public HorseRace(int nHorses, final int pause) {
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < FINISH_LINE; i++)
					s.append("=");
				System.err.println(s);
				for (Horse horse : horses)
					System.err.println(horse.tracks());
				for (Horse horse : horses)
					if (horse.getStrides() >= FINISH_LINE) {
						System.err.println(horse + "won!");
						exec.shutdownNow();
						return;
					}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (Exception e) {
					System.err.println("barrier-action sleep interrupted");
				}
			}
		});
		for (int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		}
	}
	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 200;
		new HorseRace(nHorses, pause);
	}
}
class Horse implements Runnable {
	private static int counter;
	private final int id = counter++;
	private int strides;
	private static Random rand = new Random(47);
	private CyclicBarrier barrier;
	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	public int getStrides() {
		return strides;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					strides += rand.nextInt(3);
				}
				barrier.await();
			}
		} catch (Exception e) {
		}
	}
	@Override
	public String toString() {
		return "Horse " + id + " ";
	}
	public String tracks() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < getStrides(); i++)
			result.append("*");
		result.append(id);
		return result.toString();
	}
}