package algorithm;

import java.util.Arrays;

import static algorithm.Utils.swap;

public class SelectSort {
	public static void main(String[] args) {
		int[] array = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
		asc(array);
		System.err.println(Arrays.toString(array));
		desc(array);
		System.err.println(Arrays.toString(array));
	}
	public static void asc(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] > a[j])
					swap(a, i, j);
	}
	public static void desc(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] < a[j])
					swap(a, i, j);
	}
}
