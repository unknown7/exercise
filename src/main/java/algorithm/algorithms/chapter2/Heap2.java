package algorithm.algorithms.chapter2;

public class Heap2 implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = n / 2; i >= 0; i--) {
			sink(a, i, n);
		}
		int t = n;
		for (int i = 0; i < n; i++) {
			Helper.exch(a, 0, --t);
			sink(a, 0, t);
		}
	}

	private void sink(int[] a, int k, int n) {
		while ((k + 1) * 2 <= n) {
			int j = (k + 1) * 2;
			if (j < n && a[j - 1] < a[j]) {
				j++;
			}
			if (a[k] > a[j - 1]) {
				break;
			}
			Helper.exch(a, k, j - 1);
			k = j - 1;
		}
	}
}
