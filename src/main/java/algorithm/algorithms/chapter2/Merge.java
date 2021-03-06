package algorithm.algorithms.chapter2;

public class Merge implements Sorter {

	private static int[] aux;

	@Override
	public void sort(int[] a) {
		int n = a.length;
		aux = new int[n];
		int lo = 0;
		int hi = n - 1;
		sort(a, lo, hi);
	}

	private static void sort(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		if (mid == 0 || a[mid] > a[mid + 1]) {
			merge(a, lo, mid, hi);
		}
	}


	private static void merge(int[] a, int lo, int mid, int hi) {
		// 每次merge之前都要把当前的a数组复制到aux中，而不能在初始化的时候复制到aux中
		for (int i = lo; i <= hi; i++) {
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
