package algorithm.algorithms.chapter2;

public class Bubble2 implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					Helper.exch(a, j, j + 1);
				}
			}
		}
	}
}
