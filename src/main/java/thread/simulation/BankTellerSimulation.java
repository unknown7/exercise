package thread.simulation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
	static final int MAX_LINE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE);
		exec.execute(new CustomerGenerator(customers));
		exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
		System.in.read();
		exec.shutdownNow();
	}
}
class Customer {
	private final int serviceTime;
	public Customer(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}
class CustomerLine extends ArrayBlockingQueue<Customer> {
	private static final long serialVersionUID = 1L;
	public CustomerLine(int capacity) {
		super(capacity);
	}
	@Override
	public String toString() {
		if (size() == 0)
			return "[Empty]";
		StringBuilder result = new StringBuilder();
		for (Customer customer : this)
			result.append(customer);
		return result.toString();
	}
}
class CustomerGenerator implements Runnable {
	private CustomerLine customers;
	private static Random rand = new Random(47);
	public CustomerGenerator(CustomerLine customers) {
		this.customers = customers;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		} catch (Exception e) {
		}
		System.err.println("CustomerGenerator terminating; customers" + customers);
	}
}
class Teller implements Runnable, Comparable<Teller> {
	private static int counter;
	private final int id = counter++;
	private CustomerLine customers;
	private boolean serving = true;
	private int servingNum;
	public Teller(CustomerLine customers) {
		this.customers = customers;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Customer customer = customers.take();
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					servingNum++;
					while (!serving)
						wait();
				}
			}
		} catch (Exception e) {
		}
		System.err.println(this + " terminating");
	}
	public synchronized void doSomethingElse() {
		serving = false;
		servingNum = 0;
	}
	public synchronized void serve() {
		serving = true;
		notifyAll();
	}
	@Override
	public String toString() {
		return "Teller " + id + " ";
	}
	public String shortString() {
		return "T" + id + " ";
	}
	@Override
	public synchronized int compareTo(Teller o) {
		return servingNum > o.servingNum ? 1 : (servingNum == o.servingNum ? 0 : -1);
	}
}
class TellerManager implements Runnable {
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
	private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
	private int adjustmentPeriod;
	public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
		this.exec = exec;
		this.customers = customers;
		this.adjustmentPeriod = adjustmentPeriod;
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.err.print(customers + " { ");
				for (Teller teller : workingTellers)
					System.err.print(teller.shortString() + " ");
				System.err.println("}");
			}
		} catch (Exception e) {
		}
		System.err.println(this + " terminating; workingTellers:" + workingTellers + "; tellersDoingOtherThings:" + tellersDoingOtherThings);
	}
	public void adjustTellerNumber() {
		if (customers.size() / workingTellers.size() > 2) {
			if (tellersDoingOtherThings.size() > 0) {
				Teller teller = tellersDoingOtherThings.remove();
				teller.serve();
				workingTellers.offer(teller);
				return;
			}
			Teller teller = new Teller(customers);
			exec.execute(teller);
			workingTellers.add(teller);
			return;
		}
		if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
			reassignOneTeller();
		if (customers.size() == 0)
			while (workingTellers.size() > 0)
				reassignOneTeller();
			
	}
	private void reassignOneTeller() {
		Teller teller = workingTellers.poll();
		teller.doSomethingElse();
		tellersDoingOtherThings.offer(teller);
	}
	@Override
	public String toString() {
		return "TellerManager ";
	}
}