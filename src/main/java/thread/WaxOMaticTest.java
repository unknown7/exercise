package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMaticTest {
	public static void main(String[] args) throws InterruptedException {
		CarTest car = new CarTest();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; i++) {
			exec.execute(new WaxOnTest(car));
			exec.execute(new WaxOffTest(car));
		}
		
		TimeUnit.SECONDS.sleep(8);
		exec.shutdownNow();
	}
}
class CarTest {
	private boolean waxed = false;
	public synchronized void waxed() {
		waxed = true;
		notifyAll();
	}
	public synchronized void buffed() {
		waxed = false;
		notifyAll();
	}
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxed == false)
			wait();
	}
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxed == true)
			wait();
	}
}
class WaxOnTest implements Runnable {
	private CarTest car;
	public WaxOnTest(CarTest car) {
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.err.println("Wax On!");
				TimeUnit.SECONDS.sleep(1);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (Exception e) {
			System.err.println("Interrupting from WaxOn");
		}
	}
}
class WaxOffTest implements Runnable {
	private CarTest car;
	public WaxOffTest(CarTest car) {
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.err.println("Wax Off!");
				TimeUnit.SECONDS.sleep(1);
				car.buffed();
			}
		} catch (Exception e) {
			System.err.println("Interrupting from WaxOff");
		}
	}
}