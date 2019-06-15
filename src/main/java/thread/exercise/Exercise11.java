package thread.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise11 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		UnusefulCalculate uc = new UnusefulCalculate();
		for (int i = 0; i < 500; i++)
			exec.execute(uc);
		exec.shutdown();
		
		System.err.println(uc.getA());
	}
}

class UnusefulCalculate implements Runnable {
	private int a = 2;
	private int b = 3;
//	public synchronized void calculate() {
//		a = a ^ b;
//		b = a ^ b;
//		a = a ^ b;
//		a = a ^ b;
//		b = a ^ b;
//		a = a ^ b;
//	}
	private Lock lock = new ReentrantLock();
	public void calculate() {
		lock.lock();
		try {
			a = a ^ b;
			b = a ^ b;
			a = a ^ b;
			a = a ^ b;
			b = a ^ b;
			a = a ^ b;
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void run() {
		calculate();
	}
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
}