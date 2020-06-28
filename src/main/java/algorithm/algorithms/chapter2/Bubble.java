package algorithm.algorithms.chapter2;

public class Bubble implements Sorter {

	@Override
	public void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					Helper.exch(a, j, j + 1);
				}
			}
		}
	}
}
