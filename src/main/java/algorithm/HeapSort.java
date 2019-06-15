package algorithm;

import generator.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HeapSort {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ArrayList<Integer> list = new ArrayList<Integer>(10);
		for (int i = 0; i < 10; i++)
			list.add(rand.nextInt(100));
		HeapTree ht = new HeapTree(list.toArray(new Integer[] {}));
		ht.offer(90);
		ht.offer(-1);
		ht.offer(8);
		System.err.println(ht);
	}
}

class HeapTree {
	private int[] values;
	private int length;
	private int size;

	public HeapTree(Integer... v) {
		values = Converter.convert(Arrays.copyOf(v, v.length));
		length = values.length;
		size = values.length;
		for (int i = size / 2; i >= 0; i--)
			siftdown(i);
	}

	private void siftdown(int i) {
		boolean flag = true;
		while (leftChildIndex(i) < size && flag) {
			int t;
			if (values[i] > values[leftChildIndex(i)]) {
				t = leftChildIndex(i);
			} else {
				t = i;
			}
			if (rightChildIndex(i) < size) {
				if (values[t] > values[rightChildIndex(i)]) {
					t = rightChildIndex(i);
				}
			}
			if (t != i) {
				swap(t, i);
				i = t;
			} else {
				flag = false;
			}
		}
	}
	
	private void siftup(int i) {
		boolean flag = true;
		while (flag && i >= 0) {
			int p = parentIndex(i);
			if (values[p] > values[i]) {
				swap(p, i);
				i = p;
			} else {
				flag = false;
			}
		}
	}

	private void swap(int num1, int num2) {
		values[num1] = values[num1] ^ values[num2];
		values[num2] = values[num1] ^ values[num2];
		values[num1] = values[num1] ^ values[num2];
	}

	public int poll() {
		int top = values[0];
		values[0] = values[size - 1];
		size--;
		siftdown(0);
		return top;
	}
	
	public void offer(int i) {
		ensureCapacity(size + 1);
		values[size] = i;
		siftup(size);
		size++;
	}

	private int leftChildIndex(int i) {
		return (i << 1) + 1;
	}

	private int rightChildIndex(int i) {
		return (i << 1) + 2;
	}
	
	private int parentIndex(int i) {
		return (i - 1) / 2;
	}
	
	public void ensureCapacity(int capacity) {
		if (capacity >= length) {
			int newCapacity = length + (length >> 2);
			values = Arrays.copyOf(values, newCapacity);
			length = values.length;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		while (size > 0) {
			result.append(poll());
			if (size > 0)
				result.append(", ");
		}
		return result.toString();
	}
}
