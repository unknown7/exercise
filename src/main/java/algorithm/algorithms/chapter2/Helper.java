package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Helper {

	public static void exchange(int[] a, int i, int j) {
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
		test(new Shell2());
	}
}