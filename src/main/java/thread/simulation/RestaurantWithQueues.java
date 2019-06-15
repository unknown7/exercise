package thread.simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import enumeration.Course;
import enumeration.Food;

public class RestaurantWithQueues {
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant restaurant = new Restaurant(exec, 5, 2);
		exec.execute(restaurant);
		System.in.read();
		exec.shutdownNow();
	}
}
class Order {
	private static int counter;
	private final int id = counter++;
	private final ResCustomer customer;
	private final WaitPerson waitPerson;
	private final Food food;
	public Order(ResCustomer cust, WaitPerson wp, Food f) {
		this.customer = cust;
		this.waitPerson = wp;
		this.food = f;
	}
	public Food item() { return food; }
	public ResCustomer getCustomer() { return customer; }
	public WaitPerson getWaitPerson() { return waitPerson; }
	@Override
	public String toString() {
		return "Order: " + id + " item: " + food + " for: " + customer + " served by: " + waitPerson;
	}
}
class Plate {
	private final Order order;
	private final Food food;
	public Plate(Order order, Food food) {
		this.order = order;
		this.food = food;
	}
	public Order getOrder() {
		return order;
	}
	public Food getFood() {
		return food;
	}
	@Override
	public String toString() {
		return food.toString();
	}
}
class ResCustomer implements Runnable {
	private static int counter;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>();
	public ResCustomer(WaitPerson w) { this.waitPerson = w; }
	public void deliver(Plate p) throws InterruptedException {
		placeSetting.put(p);
	}
	@Override
	public void run() {
		for (Course course : Course.values()) {
			Food food = course.randomSelection();
			try {
				waitPerson.placeOrder(this, food);
				System.err.println(this + "eating " + placeSetting.take());
			} catch (InterruptedException e) {
				System.err.println(this + "waiting for " + course + " interrupted");
				break;
			}
		}
		System.err.println(this + "finished meal. leaving");
	}
	@Override
	public String toString() {
		return "Customer " + id + " ";
	}
}
class WaitPerson implements Runnable {
	private static int counter;
	private final int id = counter++;
	private final Restaurant restaurant;
	BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();
	public WaitPerson(Restaurant rest) { this.restaurant = rest; }
	public void placeOrder(ResCustomer cust, Food food) {
		try {
			restaurant.orders.put(new Order(cust, this, food));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Plate plate = filledOrders.take();
				System.err.println(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		} catch (InterruptedException e) {
		}
		System.err.println(this + " off duty");
	}
	@Override
	public String toString() {
		return "WaitPerson " + id + " ";
	}
}
class Chef implements Runnable {
	private static int counter;
	private final int id = counter++;
	private final Restaurant restaurant;
	private static Random rand = new Random(47);
	public Chef(Restaurant rest) { restaurant = rest; }
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Order order = restaurant.orders.take();
				Food requestedItem = order.item();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				Plate plate = new Plate(order, requestedItem);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch (InterruptedException e) {
		}
		System.err.println(this + " off duty");
	}
	@Override
	public String toString() {
		return "Chef " + id + " ";
	}
}
class Restaurant implements Runnable {
	private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
	private List<Chef> chefs = new ArrayList<Chef>();
	private ExecutorService exec;
	private static Random rand = new Random(47);
	BlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();
	public Restaurant(ExecutorService e, int nWaitPersons, int nChefs) {
		exec = e;
		for (int i = 0; i < nWaitPersons; i++) {
			WaitPerson w = new WaitPerson(this);
			waitPersons.add(w);
			exec.execute(w);
		}
		for (int i = 0; i < nChefs; i++) {
			Chef c = new Chef(this);
			chefs.add(c);
			exec.execute(c);
		}
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
				ResCustomer c = new ResCustomer(wp);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
		}
		System.err.println("Restaurant closing");
	}
	
}