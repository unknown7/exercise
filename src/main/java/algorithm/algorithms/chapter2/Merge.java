package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Merge {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		sort(a);
		System.err.println(Arrays.toString(a));
	}

	private static int[] aux;

	public static void sort(int[] a) {
		int n = a.length;
		aux = new int[n];
		int lo = 0;
		int hi = n - 1;
		sort(a, lo, hi);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}


	private static void merge(int[] a, int lo, int mid, int hi) {
		// 每次merge之前都要把当前的a数组复制到aux中，而不能在初始化的时候复制到aux中
		for (int i = lo; i <= hi ; i++) {
			aux[i] = a[i];
		}
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (aux[i] > aux[j]) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}
}
