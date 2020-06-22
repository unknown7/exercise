package algorithm.algorithms.chapter2;

import java.util.Arrays;

public class Shell {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		shell(a);
		System.err.println(Arrays.toString(a));
	}

	static void sort(int[] a) {
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
					Exchanger.exchange(a, j, j - h);
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
					Exchanger.exchange(a, j, j - 1);
				}
			}
			h /= 3;
		}
	}
}
