package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		SynchronousQueue<Instance> queue = new SynchronousQueue<Instance>();
		for (int i = 0; i < 100; i++)
			exec.execute(new Producer(queue));
		exec.execute(new Consumer(queue));
	}
}
class Producer implements Runnable {
	private static int counter = 0;
	private static Lock lock = new ReentrantLock();
	private Instance[] instances = new Instance[3];
	private String[] names = { "A", "B", "C" };
	private SynchronousQueue<Instance> queue;
	public Producer(SynchronousQueue<Instance> queue) {
		this.queue = queue;
		for (int i = 0; i < 3; i++)
			instances[i] = new Instance(names[i]);
	}
	@Override
	public void run() {
		lock.lock();
		try {
			while (!Thread.interrupted())
				queue.put(instances[counter++ % instances.length]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
class Consumer implements Runnable {
	private SynchronousQueue<Instance> queue;
	public Consumer(SynchronousQueue<Instance> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Instance ins = queue.take();
				ins.run();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Instance implements Runnable {
	private final String id;
	public Instance(String id) {
		this.id = id;
	}
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
			System.err.print(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}