package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Helper {

	public static void exch(int[] a, int i, int j) {
		if (i == j) return;
		a[i] = a[i] ^ a[j];
		a[j] = a[i] ^ a[j];
		a[i] = a[i] ^ a[j];
	}

	public static void test(Sorter sorter) {
		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		sorter.sort(a);
		System.err.println(Arrays.toString(a));
	}

	public static void main(String[] args) {
		test(new Heap2());

		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		MaxPriorityQueue2 pq = new MaxPriorityQueue2(a);
		System.err.println("capacity:" + pq.capacity());
		pq.insert(6);
		System.err.println("capacity:" + pq.capacity());
		while (!pq.isEmpty()) {
			System.err.print(pq.delMax());
			if (!pq.isEmpty()) {
				System.err.print(", ");
			}
		}
	}
}
