package thread.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise24Test {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ItemQueueTest<ItemTest> queue = new ItemQueueTest<ItemTest>(10);
		exec.execute(new ProducerTest(queue, 1));
		exec.execute(new ConsumerTest(queue, 3));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}
class ItemTest {
	private static int count;
	private final int id = count++;
	public String toString() { return "Item " + id; };
}
class ItemQueueTest<T> {
	private Queue<T> queue = new LinkedList<T>();
	private int size;
	public ItemQueueTest(int size) {
		this.size = size;
	}
	public synchronized void offer(T e) {
		try {
			if (queue.size() >= size)
				wait();
			queue.offer(e);
			System.err.println(e + " in queue");
			notifyAll();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	public synchronized T poll() {
		try {
			if (queue.isEmpty())
				wait();
			notifyAll();
			return queue.poll();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
class ProducerTest implements Runnable {
	private ItemQueueTest<ItemTest> queue;
	private int delay;
	ProducerTest(ItemQueueTest<ItemTest> queue, int delay) {
		this.queue = queue;
		this.delay = delay;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.SECONDS.sleep(delay);
				ItemTest item = new ItemTest();
				queue.offer(item);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ConsumerTest implements Runnable {
	private ItemQueueTest<ItemTest> queue;
	private int delay;
	ConsumerTest(ItemQueueTest<ItemTest> queue, int delay) {
		this.queue = queue;
		this.delay = delay;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.SECONDS.sleep(delay);
				System.err.println(queue.poll());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


