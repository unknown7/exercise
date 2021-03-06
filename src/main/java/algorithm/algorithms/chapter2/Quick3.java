package algorithm.algorithms.chapter2;

public class Quick3 implements Sorter {
	@Override
	public void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(int[] a, int lo, int hi) {
		if (lo >= hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private int partition(int[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		int t = a[lo];
		while (true) {
			while (a[++i] < t) {
				if (i == hi) {
					break;
				}
			}
			while (a[--j] > t) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			Helper.exch(a, i, j);
		}
		Helper.exch(a, lo, j);
		return j;
	}
}
