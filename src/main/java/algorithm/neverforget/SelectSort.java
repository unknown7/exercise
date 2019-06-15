package algorithm.neverforget;

import java.util.Arrays;

public class SelectSort {
	public static void main(String[] args) {
		int[] a = { 9, 5, 3, 7, 8, 2, 1, 4, 6 };
		asc(a);
		System.err.println(Arrays.toString(a));
	}
	public static void asc(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					a[i] = a[i] ^ a[j];
					a[j] = a[i] ^ a[j];
					a[i] = a[i] ^ a[j];
				}
			}
		}
	}
}
