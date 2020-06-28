package algorithm.algorithms.chapter2;

public class Quick implements Sorter {

	@Override
	public void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (lo >= hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(int[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		int v = a[lo];
		while (true) {
			while (a[++i] < v) {
				if (i == hi) {
					break;
				}
			}

			while (a[--j] > v) {
				if (j == lo) {
					break;
				}
			}

			if (i >= j) break;

			Helper.exchange(a, i, j);
		}
		Helper.exchange(a, lo, j);
		return j;
	}
}