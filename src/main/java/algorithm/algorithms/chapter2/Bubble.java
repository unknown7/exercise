package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Bubble {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		sort(a);
		System.err.println(Arrays.toString(a));
	}

	static void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					Exchanger.exchange(a, j, j + 1);
				}
			}
		}
	}
}
