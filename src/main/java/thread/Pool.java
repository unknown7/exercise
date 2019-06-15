package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkOut;
	private Semaphore available;
	public Pool(Class<T> clazz, int size) {
		this.size = size;
		checkOut = new boolean[size];
		available = new Semaphore(size, true);
		for (int i = 0; i < size; i++) {
			try {
				items.add(clazz.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	public T checkOut() throws InterruptedException {
		available.acquire();
		return getItem();
	}
	private synchronized T getItem() {
		for (int i = 0; i < size; i++) {
			if (!checkOut[i]) {
				checkOut[i] = true;
				return items.get(i);
			}
		}
		return null;
	}
	public void checkIn(T item) {
		if (releaseItem(item))
			available.release();
	}
	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (index == -1) return false;
		if (checkOut[index]) {
			checkOut[index] = false;
			return true;
		}
		return false;
	}
}
