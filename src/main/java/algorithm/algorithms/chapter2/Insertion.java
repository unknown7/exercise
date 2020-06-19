package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Insertion {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9 ,2, 19, 22, 24, 21, 11, 283, 123};
		sort(a);
		System.err.println(Arrays.toString(a));
	}

	static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					Exchanger.exchange(a, j, j - 1);
				}
			}
		}
	}
}
