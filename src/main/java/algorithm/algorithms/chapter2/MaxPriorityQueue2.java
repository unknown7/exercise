package algorithm.algorithms.chapter2;

public class MaxPriorityQueue2 {

	private int[] pq;
	private int n;

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

	}
}
