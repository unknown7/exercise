package algorithm;

import java.util.Arrays;

import static algorithm.Utils.swap;

public class BubbleSort {
	public static void main(String[] args) {
		int[] array = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
		asc(array);
		System.err.println(Arrays.toString(array));
		desc(array);
		System.err.println(Arrays.toString(array));
	}
	public static void asc(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = 0; j < a.length - 1 - i; j++)
				if (a[j] > a[j + 1])
					swap(a, j, j + 1);
	}
	public static void desc(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = 0; j < a.length - 1 - i; j++)
				if (a[j] < a[j + 1])
					swap(a, j, j + 1);
	}
}
