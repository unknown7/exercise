package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiftOff implements Runnable {
	protected int countDown = 3;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public LiftOff() {}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
	}
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.err.print(status());
			Thread.yield();
		}
	}
	
	public static void main(String[] args) throws Exception {
		final int SIZE = 3;
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			queue.put(i);
		}
		Integer take = queue.take();
		System.err.println(queue.size());
		queue.put(3);
		System.err.println(queue.size());

		final Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		new Thread() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
					lock.lock();
					System.err.println("acquire lock.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		lock.lock();
		condition.await();
	}
}
