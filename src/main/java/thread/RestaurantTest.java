package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RestaurantTest {
	MealTest meal;
	WaitPersonTest waitPerson = new WaitPersonTest(this);
	ChefTest chef = new ChefTest(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	RestaurantTest() {
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	public static void main(String[] args) {
		new RestaurantTest();
	}
}
class MealTest {
	private int id;
	public MealTest(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Meal " + id;
	}
}
class WaitPersonTest implements Runnable {
	private RestaurantTest restaurant;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	WaitPersonTest(RestaurantTest restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					while (restaurant.meal == null)
						condition.await();
				} finally {
					lock.unlock();
				}
				System.err.println("Got " + restaurant.meal);
				restaurant.chef.lock.lock();
				try {
					restaurant.meal = null;
					restaurant.chef.condition.signalAll();
				} finally {
					restaurant.chef.lock.unlock();
				}
			}
		} catch (InterruptedException e) {
		}
	}
}
class ChefTest implements Runnable {
	private RestaurantTest restaurant;
	private int count;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	ChefTest(RestaurantTest restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					while (restaurant.meal != null)
						condition.await();
				} finally {
					lock.unlock();
				}
				if (++count == 10) {
					System.err.println("Out of food, closing!");
					restaurant.exec.shutdownNow();
				}
				restaurant.waitPerson.lock.lock();
				try {
					restaurant.meal = new MealTest(count);
					restaurant.waitPerson.condition.signalAll();
				} finally {
					restaurant.waitPerson.lock.unlock();
				}
			}
		} catch (InterruptedException e) {
		}
	}
}

