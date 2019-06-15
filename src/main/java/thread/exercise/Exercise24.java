package thread.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise24 {
	public static void main(String[] args) throws InterruptedException {
		ItemQueue<Item> queue = new ItemQueue<Item>(10);
		ExecutorService exec = Executors.newFixedThreadPool(2);
		exec.execute(new Producer(queue, 1));
		exec.execute(new Consumer(queue, 3));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}
class Item {
	private static int count = 0;
	private int id = count++;
	@Override
	public String toString() { return "Item " + id; }
}
class ItemQueue<T> {
	private Queue<T> queue = new LinkedList<T>();
	private int maxSize;
	public ItemQueue(int maxSize) { this.maxSize = maxSize; }
	public synchronized void put(T item) throws Exception {
		while (queue.size() >= maxSize)
			wait();
		queue.offer(item);
		notifyAll();
	}
	public synchronized T get() throws Exception {
		while (queue.isEmpty())
			wait();
		T e = queue.poll();
		notifyAll();
		return e;
	}
	public int size() {
		return queue.size();
	}
}
class Producer implements Runnable {
	private ItemQueue<Item> queue;
	private int delay;
	public Producer(ItemQueue<Item> queue, int delay) {
		this.queue = queue;
		this.delay = delay;
	}
	@Override
	public void run() {
		try {
			for (;;) {
				Item e = new Item();
				queue.put(e);
				System.err.println("offer " + e);
				TimeUnit.SECONDS.sleep(delay);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
class Consumer implements Runnable {
	private ItemQueue<Item> queue;
	private int delay;
	public Consumer(ItemQueue<Item> queue, int delay) {
		this.queue = queue;
		this.delay = delay;
	}
	@Override
	public void run() {
		try {
			for (;;) {
				System.err.println("poll " + queue.get() + ", queue size: " + queue.size());
				TimeUnit.SECONDS.sleep(delay);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}