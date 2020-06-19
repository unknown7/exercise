package algorithm.algorithms.chapter2;

public class Exchanger {

	public static void exchange(int[] a, int i, int j) {
		if (i == j) return;
		a[i] = a[i] ^ a[j];
		a[j] = a[i] ^ a[j];
		a[i] = a[i] ^ a[j];
	}
}
