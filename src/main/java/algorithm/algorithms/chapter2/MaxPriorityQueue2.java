package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class MaxPriorityQueue2 {

	private int[] pq;
	private int n;
	private int capacity;

	public MaxPriorityQueue2(int[] a) {
		n = a.length;
		pq = new int[n + 1];
		capacity = pq.length;
		for (int i = 1; i <= n; i++) {
			pq[i] = a[i - 1];
		}
		for (int i = n / 2; i > 0; i--) {
			sink(i);
		}
	}

	public int delMax() {
		int max = pq[1];
		ensureCapacity(n - 1);
		Helper.exch(pq, 1, n--);
		sink(1);
		return max;
	}

	public void insert(int t) {
		ensureCapacity(n + 1);
		pq[++n] = t;
		swim(n);
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int capacity() {
		return capacity;
	}

	public int size() {
		return n;
	}

	private void ensureCapacity(int n) {
		int[] newArray = pq;
		if (n >= capacity) {
			capacity = capacity * 2;
			newArray = Arrays.copyOf(this.pq, capacity);
		} else if (n < capacity / 4) {
			capacity = capacity / 2;
			newArray = Arrays.copyOf(this.pq, capacity);
		}
		pq = newArray;
	}

	private void swim(int k) {
		while (k > 1) {
			if (pq[k] < pq[k / 2]) {
				break;
			}
			Helper.exch(pq, k, k / 2);
			k /= 2;
		}
	}

	private void sink(int k) {
		while (k * 2 <= n) {
			int j = k * 2;
			if (j < n && pq[j] < pq[j + 1]) {
				j++;
			}
			if (pq[k] > pq[j]) {
				break;
			}
			Helper.exch(pq, k, j);
			k = j;
		}
	}
}
