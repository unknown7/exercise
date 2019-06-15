package collection.arraylist;

import java.util.List;

import generator.Generator;
import generator.RandomGenerator;

public class Main {
	public static void main(String[] args) {
		Generator<Integer> gen = new RandomGenerator.Integer();
		
		List<Integer> list = new SimpleArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			list.add(gen.next());
		}
		
		System.err.println(list);
	}
}
