package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaxOMatic2 {
	static class Car {
		private boolean waxOn = false;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		public void waxed() {
			lock.lock();
			try {
				waxOn = true;
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		}
		public void buffed() {
			lock.lock();
			try {
				waxOn = false;
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		}
		public void waitForWaxing() throws InterruptedException {
			lock.lock();
			try {
				while (waxOn == false)
					condition.await();
			} finally {
				lock.unlock();
			}
		}
		public void waitForBuffing() throws InterruptedException {
			lock.lock();
			try {
				while (waxOn == true)
					condition.await();
			} finally {
				lock.unlock();
			}
		}
	}
	static class WaxOn implements Runnable {
		private Car car;
		public WaxOn(Car car) {
			this.car = car;
		}
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {
					System.err.println("WaxOn!");
					TimeUnit.SECONDS.sleep(1);
					car.waxed();
					car.waitForBuffing();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			System.err.println("Exit from WaxOn");
		}
	}
	static class WaxOff implements Runnable {
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
					TimeUnit.SECONDS.sleep(1);
					car.buffed();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			System.err.println("Exit from WaxOff");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Car car = new Car();
		exec.execute(new WaxOn(car));
		exec.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}
