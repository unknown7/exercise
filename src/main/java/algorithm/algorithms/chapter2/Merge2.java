package algorithm.algorithms.chapter2;

public class Merge2 implements Sorter {

	private static int[] aux;

	@Override
	public void sort(int[] a) {
		int n = a.length;
		aux = new int[n];
		sort(a, 0, n  -1);
	}

	private void sort(int[] a, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		if (mid == 0 || a[mid] > a[mid + 1]) {
			merge(a, lo, mid, hi);
		}
	}

	private void merge(int[] a, int lo, int mid, int hi) {
		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}
		int i = lo, j = mid + 1;
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
