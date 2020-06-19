package algorithm.algorithms.chapter2;

import algorithm.Utils;

import java.util.Arrays;

public class SelectSort {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9 ,2, 19, 22, 24, 21, 11};
		sort(a);
		System.err.println(Arrays.toString(a));
	}

	static void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			Utils.swap(a, i, min);
		}
	}
}
