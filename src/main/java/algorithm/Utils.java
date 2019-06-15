package algorithm;

public class Utils {
	public static void swap(int[] a, int index1, int index2) {
		a[index1] = a[index1] ^ a[index2];
		a[index2] = a[index1] ^ a[index2];
		a[index1] = a[index1] ^ a[index2];
	}
}
