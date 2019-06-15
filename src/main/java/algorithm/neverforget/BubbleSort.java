package algorithm.neverforget;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] a = { 9, 5, 3, 7, 8, 2, 1, 4, 6 };
		asc(a);
		System.err.println(Arrays.toString(a));
	}
	public static void asc(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					a[j] = a[j] ^ a[j + 1];
					a[j + 1] = a[j] ^ a[j + 1];
					a[j] = a[j] ^ a[j + 1];
				}
			}
		}
	}
}
