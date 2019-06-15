package datastructure;

import generator.Countries;

import java.util.Arrays;
import java.util.Random;

public class Array {
	private String[] array;
	private int index;
	public Array() {
		array = new String[7];
	}
	public Array(int size) {
		array = new String[size];
	}
	public void add(String o) {
		array[index++] = o;
	}
	public String get(int i) {
		if (i < 0 || i >= index)
			throw new ArrayIndexOutOfBoundsException();
		return array[i];
	}
	public int indexOf(String o) {
		for (int i = 0; i < index - 1; i++)
			if (array[i].equals(o))
				return i;
		return -1;
	}
	public void remove(String o) {
		int i = indexOf(o);
		if (i != -1)
			for (int j = i; j < index - 1; j++)
				array[j] = array[j + 1];
		
	}
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		Array array = new Array();
		for (int i = 0; i < 7; i++)
			array.add(Countries.names().get(random.nextInt(Countries.DATA.length)));
		
		System.err.println(array);
	}
}
