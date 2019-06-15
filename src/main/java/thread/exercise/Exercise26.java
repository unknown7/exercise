package thread.exercise;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Exercise26 {
	BlockingQueue<Meal> mealQueue = new LinkedBlockingQueue<Meal>();
	BlockingQueue<Leftover> leftoverQueue = new LinkedBlockingQueue<Leftover>();
	ExecutorService exec = Executors.newCachedThreadPool();
	Future<?> waiter;
	Future<?> chef;
	Future<?> busboy;
	public Exercise26() {
		waiter = exec.submit(new Waiter(this));
		chef = exec.submit(new Chef(this));
		busboy = exec.submit(new BusBoy(this));
	}
	public static void main(String[] args) {
		new Exercise26();
	}
}
class Meal {
	private final int id;
	public Meal(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Meal " + id;
	}
}
class Leftover {
	private int mealId;
	public Leftover(int mealId) {
		this.mealId = mealId;
	}
	@Override
	public String toString() {
		return "Leftover " + mealId;
	}
}
class Waiter implements Runnable {
	Exercise26 r;
	public Waiter(Exercise26 r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Meal meal = r.mealQueue.take();
				System.err.println("Waiter got " + meal);
				r.leftoverQueue.put(new Leftover(meal.getId()));
			}
		} catch (Exception e) {
			System.err.println("Waiter interrupt!");
		}
	}
}
class Chef implements Runnable {
	Exercise26 r;
	private int count;
	public Chef(Exercise26 r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				if (++count > 10) {
					System.err.println("Chef out of capacity!");
					r.chef.cancel(true);
					TimeUnit.MILLISECONDS.sleep(1);
				}
				Meal meal = new Meal(count);
				System.err.println("Chef produce " + meal);
				r.mealQueue.put(meal);
				Thread.yield();
			}
		} catch (Exception e) {
			System.err.println("Chef interrupt!");
		}
	}
}
class BusBoy implements Runnable {
	Exercise26 r;
	public BusBoy(Exercise26 r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Leftover leftover = r.leftoverQueue.take();
				System.err.println("BusBoy handle " + leftover);
			}
		} catch (Exception e) {
			System.err.println("BusBoy interrupt!");
		}
	}
}