package algorithm;

import java.util.Arrays;

public class InsertSort {
	public static void main(String[] args) {
		int[] array = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
		asc(array);
		System.err.println(Arrays.toString(array));
		desc(array);
		System.err.println(Arrays.toString(array));
	}
	public static void asc(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int compare = i;
			while (compare > 0 && array[compare - 1] > temp) {
				array[compare] = array[compare - 1];
				compare--;
			}
			array[compare] = temp;
		}
	}
	public static void desc(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int compare = i;
			while (compare > 0 && array[compare - 1] < temp) {
				array[compare] = array[compare - 1];
				compare--;
			}
			array[compare] = temp;
		}
	}
}
