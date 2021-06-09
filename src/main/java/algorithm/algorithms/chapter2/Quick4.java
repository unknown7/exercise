package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Quick4 {

	public void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(int[] a, int low, int high) {
		if (low >= high)
			return;
		int j = partition(a, low, high);
		sort(a, low, j - 1);
		sort(a, j + 1, high);
	}

	private int partition(int[] a, int low, int high) {
		int i = low, j = high + 1;
		int value = a[low];
		while (true) {

			while (a[++i] < value) {
				if (i == high) {
					break;
				}
			}

			while (a[--j] > value) {
				if (j == low) {
					break;
				}
			}

			if (i >= j)
				break;

			Helper.exch(a, i, j);
		}

		Helper.exch(a, low, j);
		return j;
	}

	public static void main(String[] args) {
		int[] a = {3, 2, 1, 5, 8, 4, 13, 10, 12, 11};

		new Quick4().sort(a);

		System.err.println(Arrays.toString(a));
	}
}
