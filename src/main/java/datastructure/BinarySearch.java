package datastructure;

import java.util.Arrays;

public class BinarySearch {
	private int[] array;
	private int index;
	public BinarySearch() {
		array = new int[7];
	}
	public BinarySearch(int size) {
		array = new int[size];
	}
	public void add(int o) {
		array[index++] = o;
	}
	public int get(int i) {
		if (i < 0 || i >= index)
			throw new ArrayIndexOutOfBoundsException();
		return array[i];
	}
	public int indexOf(int o) {
		for (int i = 0; i < index - 1; i++)
			if (array[i] == o)
				return i;
		return -1;
	}
	public void remove(int o) {
		int i = indexOf(o);
		if (i != -1)
			for (int j = i; j < index - 1; j++)
				array[j] = array[j + 1];
	}
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
	public int binarySearch(int o) {
		int start = 0;
		int end = index - 1;
		while (end > start) {
			int i = (start + end) / 2;
			if (o < array[i])
				end = i - 1;
			else if (o > array[i])
				start = i + 1;
			else
				return i;
		}
		return -1;
	}
}
