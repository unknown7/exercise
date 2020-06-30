package algorithm.algorithms.chapter3;

import algorithm.algorithms.chapter2.Quick;

import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {7, 3, 1, 9, 2, 19, 22, 24, 21, 11, 283, 123};
		int t = 7;
		int index = search(a, t);
		System.err.println("index of '" + t + "' : " +index);
	}

	private static int search(int[] a, int t) {
		new Quick().sort(a);
		System.err.println(Arrays.toString(a));
		int recursion = recursion(a, t);
		int loop = loop(a, t);
		if (recursion != loop) {
			throw new RuntimeException();
		}
		return loop;
	}

	private static int loop(int[] a, int t) {
		int i = 0, j = a.length - 1;
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (a[mid] < t) {
				i = mid + 1;
			} else if (a[mid] > t) {
				j = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private static int recursion(int[] a, int t) {
		return recursion(a, t, 0, a.length - 1);
	}

	private static int recursion(int[] a, int t, int lo, int hi) {
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (a[mid] < t) {
			return recursion(a, t, mid + 1, hi);
		} else if (a[mid] > t) {
			return recursion(a, t, lo, mid - 1);
		} else {
			return mid;
		}
	}
}
