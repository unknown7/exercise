package algorithm;

public class Utils {
	public static void swap(int[] a, int i, int j) {
		if (i == j) return;
		a[i] = a[i] ^ a[j];
		a[j] = a[i] ^ a[j];
		a[i] = a[i] ^ a[j];
	}
}
