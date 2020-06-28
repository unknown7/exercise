package algorithm.algorithms.chapter2;

public class Heap implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = n / 2; i >= 0; i--) {
			sink(a, i + 1, n);
		}
		int t = n;
		for (int i = 0; i < n; i++) {
			Helper.exch(a, 0, --t);
			sink(a, 1, t);
		}
	}

	private void sink(int[] a, int k, int n) {
		while (k * 2 <= n) {
			int j = k * 2;
			if (j < n && a[j - 1] < a[j]) {
				j++;
			}
			if (a[k - 1] > a[j - 1]) {
				break;
			}
			Helper.exch(a, k - 1, j - 1);
			k = j;
		}
	}
}
