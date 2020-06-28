package algorithm.algorithms.chapter2;

public class MaxPriorityQueue {

	private int[] pq;
	private int n;

	public MaxPriorityQueue(int n) {
		pq = new int[n + 1];
	}

	public MaxPriorityQueue(int[] a) {
		pq = new int[a.length + 1];
		n = a.length;
		for (int i = 1; i < pq.length; i++) {
			pq[i] = a[i - 1];
		}
		for (int i = n / 2; i > 0; i--) {
			sink(i);
		}
	}

	public void insert(int a) {
		pq[++n] = a;
		swim(n);
	}

	public int delMax() {
		int max = pq[1];
		Helper.exch(pq, 1, n--);
		sink(1);
		return max;
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
				j += 1;
			}
			if (pq[k] > pq[j]) {
				break;
			}
			Helper.exch(pq, k, j);
			k = j;
		}
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}
}
