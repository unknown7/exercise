package algorithm.algorithms.chapter2;

public class Merge {


	static void merge(int[] a, int lo, int mid, int hi) {
		int[] aux = new int[a.length];
		// copy
		for (int i = lo; i <= hi ; i++) {
			aux[i] = a[i];
		}
		int i = lo;
		int j = mid + 1;
		for (int k = 0; k <= hi; k++) {
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
