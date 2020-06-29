package algorithm.algorithms.chapter2;

public class Shell2 implements Sorter {
	@Override
	public void sort(int[] a) {
		int n = a.length;
		int k = 1;
		while (k < n / 3) {
			k = k * 3 + 1;
		}
		while (k > 0) {
			for (int i = k; i < n; i++) {
				for (int j = i; j >= k && a[j] < a[j - k]; j-=k) {
					Helper.exch(a, j, j - k);
				}
			}
			k /= 3;
		}
	}
}
