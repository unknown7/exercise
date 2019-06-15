package map;

import generator.Generator;
import generator.RandomGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		long start = new Date().getTime();
//		SuperSlowMap<Integer, String> map = new SuperSlowMap<Integer, String>();
//		Map<Integer, String> map = new SlowMap<Integer, String>();
//		Map<Integer, String> map = new SimpleHashMap<Integer, String>();
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		Generator<Integer> genK = new RandomGenerator.Integer(100);
		Generator<String> genV = new RandomGenerator.String();
		for (int i = 0; i < 100; i++)
			map.put(genK.next(), genV.next());
		System.err.println(map.get(1));
		long end = new Date().getTime();
		System.err.println("use: " + (end - start));
	}
}
