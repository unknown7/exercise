package algorithm.algorithms.chapter2;

public class Selection2 implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			Helper.exchange(a, i, min);
		}
	}
}
