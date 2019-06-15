package algorithm.neverforget;

import java.util.Arrays;

public class InsertSort {
	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
		asc(a);
		System.err.println(Arrays.toString(a));
		desc(a);
		System.err.println(Arrays.toString(a));
	}
	public static void asc(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int compare = i;
			while (compare > 0 && a[compare - 1] > temp) {
				a[compare] = a[compare - 1];
				compare--;
			}
			a[compare] = temp;
		}
	}
	public static void desc(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int compare = i;
			while (compare > 0 && a[compare - 1] < temp) {
				a[compare] = a[compare - 1];
				compare--;
			}
			a[compare] = temp;
		}
	}
}
