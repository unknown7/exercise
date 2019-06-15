package datastructure;

import static algorithm.Utils.swap;

import java.util.Arrays;

public class BinaryTree {
	static int[] a = { 1, 4, 2, 5, 7, 3, 17, 8, 9, 6, 11, 10, 16, 12 };
	static int size = a.length;
	public static void main(String[] args) {
		create();
		System.err.println(Arrays.toString(a));
		System.err.println(new BinaryTree());
	}
	public static void create() {
		int lastFatherNodeIndex = a.length >> 1;
		for (int i = lastFatherNodeIndex; i >= 0; i--)
			siftdown(i);
	}
	public static void siftdown(int i) {
		int t, flag = 0;
		while (leftSonIndex(i) < size && flag == 0) {
			if (a[i] > a[leftSonIndex(i)])
				t = leftSonIndex(i);
			else
				t = i;
			
			if (rightSonIndex(i) < size)
				if (a[t] > a[rightSonIndex(i)])
					t = rightSonIndex(i);
			
			if (t != i) {
				swap(a, i, t);
				i = t;
			} else
				flag = 1;
		}
	}
	public static void siftup(int i) {
		int flag = 0;
		if (i == 0)
			return;
		
		while (i != 0 && flag == 0) {
			if (a[i] < a[i >> 1])
				swap(a, i, i >> 1);
			else
				flag = 1;
			
			i >>= 1;
		}
	}
	public static int peek() {
		return a[0];
	}
	public static int pop() {
		int t = a[0];
		a[0] = a[size - 1];
		size--;
		siftdown(0);
		return t;
	}
	private static int leftSonIndex(int i) {
		/**
		 * 此处若
		 * ((i + 1) << 1) - 1
		 * 则先左移1位，后减1
		 * 
		 * 若
		 * (i + 1) << 1 - 1
		 * 则先减1，后左移，因为先减1，故左移0位，
		 * siftdown时，leftson为current+1,rightson为current+1+1,
		 * 成为相邻三个数的比较，即冒泡排序
		 * amazing!
		 */
		return ((i + 1) << 1) - 1;
	}
	private static int rightSonIndex(int i) {
		return leftSonIndex(i) + 1;
	}
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append('[');
		for (;;) {
			buf.append(pop());
			if (size == 0)
				return buf.append(']').toString();
			buf.append(',').append(' ');
		}
	}
}
