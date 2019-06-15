package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Car car = new Car();
		exec.execute(new WaxOn(car));
		exec.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
class Car {
	private boolean waxOn = false;
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false)
			wait();
	}
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true)
			wait();
	}
}
class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car car) {
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.err.println("WaxOn!");
				TimeUnit.MILLISECONDS.sleep(100);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.err.println("Exiting via interrupt");
		}
		System.err.println("Ending Wax On task");
	}
}
class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car car) {
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.err.println("WaxOff!");
				TimeUnit.MILLISECONDS.sleep(100);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.err.println("Exiting via interrupt");
		}
		System.err.println("Ending Wax Off task");
	}
}