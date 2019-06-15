package algorithm.neverforget;

import static algorithm.Utils.swap;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
		asc(a);
		System.err.println(Arrays.toString(a));
		desc(a);
		System.err.println(Arrays.toString(a));
	}
	public static void asc(int[] a) {
		asc(a, 0, a.length - 1);
	}
	public static void asc(int[] a, int left, int right) {
		int i, j, temp;
		if (left > right)
			return;
		temp = a[left];
		i = left;
		j = right;
		while (i != j) {
			while (a[j] >= temp && i < j)
				j--;
			while (a[i] <= temp && i < j)
				i++;
			if (i < j)
				swap(a, i, j);
		}
		a[left] = a[i];
		a[i] = temp;
		
		asc(a, left, i - 1);
		asc(a, i + 1, right);
	}
	public static void desc(int[] a) {
		desc(a, 0, a.length - 1);
	}
	public static void desc(int[] a, int left, int right) {
		int i, j, temp;
		if (left > right)
			return;
		temp = a[left];
		i = left;
		j = right;
		while (i != j) {
			while (a[j] <= temp && i < j)
				j--;
			while (a[i] >= temp && i < j)
				i++;
			if (i < j)
				swap(a, i, j);
		}
		a[left] = a[i];
		a[i] = temp;
		
		desc(a, left, i - 1);
		desc(a, i + 1, right);
	}
}
