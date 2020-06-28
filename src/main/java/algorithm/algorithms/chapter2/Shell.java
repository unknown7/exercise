package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Shell implements Sorter {

	@Override
	public void sort(int[] a) {
		int n = a.length;
		int h = 1;

		// 4, 13, 40, 121, 364, 1093...
		while (h < n / 3) {
			h = 3 * h + 1;
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
					System.err.println("h:" + h + " " + Arrays.toString(a));
					Helper.exch(a, j, j - h);
				}
			}
			h /= 3;
		}
	}

	static void shell(int[] a) {
		int n = a.length;
		int h = 1;
		while (h <= n / 3) {
			h = h * 3 + 1;
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
					Helper.exch(a, j, j - 1);
				}
			}
			h /= 3;
		}
	}
}
