package algorithm.algorithms.chapter2;

public class Insertion implements Sorter {

	public void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			/**
			 * 中断循环，值得思考，nice
			 */
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				Helper.exch(a, j, j - 1);
			}
		}
	}
}
