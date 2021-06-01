package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

	private T[] queue;

	private int capacity;

	private int size;

	private Lock lock;

	private Condition wCondition;

	private Condition rCondition;

	public MyBlockingQueue(Integer capacity) {
		this.capacity = capacity;
		this.queue = (T[]) new Object[this.capacity];
		this.lock = new ReentrantLock();
		this.wCondition = lock.newCondition();
		this.rCondition = lock.newCondition();
	}

	public MyBlockingQueue() {
		this.capacity = 10;
		this.queue = (T[]) new Object[this.capacity];
		this.lock = new ReentrantLock();
		this.wCondition = lock.newCondition();
		this.rCondition = lock.newCondition();
	}

	public void add(T t) {
		try {
			lock.lock();
			while (this.size >= capacity) {
				wCondition.await();
			}
			queue[size++] = t;
			rCondition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		try {
			lock.lock();
			while (this.size <= 0) {
				rCondition.await();
			}
			T t = queue[--size];
			wCondition.signalAll();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		return this.size;
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();

		MyBlockingQueue<Integer> queue = new MyBlockingQueue<>();

		exec.execute(() -> {
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				queue.add(i);
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		for (int i = 0; i < 2; i++) {
			exec.execute(() -> {
				while (true) {
					Integer i1 = queue.get();
					System.err.println(Thread.currentThread().getName() + " get " + i1);
				}
			});
		}
	}
}
