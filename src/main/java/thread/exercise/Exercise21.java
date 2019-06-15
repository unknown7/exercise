package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise21 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Person p = new Person();
		exec.execute(new Day(p));
		exec.execute(new Night(p));
		TimeUnit.SECONDS.sleep(6);
		exec.shutdownNow();
	}
}
class Person {
	private boolean waked = false;
	public synchronized void sleep() {
		waked = false;
		notifyAll();
	}
	public synchronized void wakeup() {
		waked = true;
		notifyAll();
	}
	public synchronized void waitForSleep() throws InterruptedException {
		while (waked == true)
			wait();
	}
	public synchronized void waitForWakeup() throws InterruptedException {
		while (waked == false)
			wait();
	}
}
class Day implements Runnable {
	private Person p;
	public Day(Person p) {
		this.p = p;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.err.println("duaring the day");
				TimeUnit.SECONDS.sleep(2);
				p.wakeup();
				p.waitForSleep();
			}
		} catch (InterruptedException e) {
			System.err.println("Exit");
		}
	}
}
class Night implements Runnable {
	private Person p;
	public Night(Person p) {
		this.p = p;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				p.waitForWakeup();
				System.err.println("during the night");
				TimeUnit.SECONDS.sleep(1);
				p.sleep();
			}
		} catch (InterruptedException e) {
			System.err.println("Exit");
		}
	}
}