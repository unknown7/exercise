package collection;

import generator.Generator;
import generator.RandomGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Counter counter = new Counter();
		Generator<Character> gen = new RandomGenerator.Character();
		for (int i = 0; i < 10000; i++)
			counter.put(String.valueOf(gen.next()));
		
		counter.trim2Size();
		System.err.println(counter);
		
		Set<Integer> set = new SlowSet<Integer>();
		for (int i = 0; i < 10; i++)
			set.add(i);
		
		set.add(1);
		System.err.println(set);
		
		Collection<String> c = new ArrayList<String>();
		Iterator<String> it = c.iterator();
		c.add("An object");
		System.err.println(it.next());
	}
}
