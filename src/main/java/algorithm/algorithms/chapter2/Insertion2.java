package algorithm.algorithms.chapter2;

public class Insertion2 implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				Helper.exch(a, j, j - 1);
			}
		}
	}
}
