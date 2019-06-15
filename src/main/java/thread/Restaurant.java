package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	public Meal meal;
	public Leftover leftover;
	public Chef c = new Chef(this);
	public Waiter w = new Waiter(this);
	public BusBoy b = new BusBoy(this);
	public ExecutorService exec = Executors.newCachedThreadPool();
	public Restaurant() {
		exec.execute(c);
		exec.execute(w);
		exec.execute(b);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}
class Meal {
	private int id;
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
	private Restaurant r;
	public Waiter(Restaurant r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (r.meal == null)
						wait();
				}
				System.err.println("Waiter got " + r.meal);
				synchronized (r.c) {
					TimeUnit.SECONDS.sleep(2);
					synchronized (r.b) {
						r.leftover = new Leftover(r.meal.getId());
						r.b.notifyAll();
					}
					r.meal = null;
					r.c.notifyAll();
				}
			}
		} catch (Exception e) {
		}
	}
}
class Chef implements Runnable {
	private Restaurant r;
	private int count;
	public Chef(Restaurant r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (r.meal != null)
						wait();
				}
				if (++count > 10) {
					System.err.println("Out of capacity");
					r.exec.shutdownNow();
				}
				synchronized (r.w) {
					r.meal = new Meal(count);
					System.err.println("Chef produce " + r.meal);
					r.w.notifyAll();
				}
			}
		} catch (Exception e) {
		}
	}
}
class BusBoy implements Runnable {
	private Restaurant r;
	public BusBoy(Restaurant r) {
		this.r = r;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (r.leftover == null)
						wait();
				}
				System.err.println("BusBoy got " + r.leftover);
				synchronized (r.b) {
					r.leftover = null;
				}
			}
		} catch (Exception e) {
		}
	}
}