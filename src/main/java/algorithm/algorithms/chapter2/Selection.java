package algorithm.algorithms.chapter2;

public class Selection implements Sorter {

	public void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			Helper.exch(a, i, min);
		}
	}
}
