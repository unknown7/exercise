package map;

import generator.Generator;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class MapData<K, V> extends LinkedHashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MapData(Generator<Pairs<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pairs<K, V> pairs = gen.next();
			put(pairs.key, pairs.value);
		}
	}

	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++)
			put(genK.next(), genV.next());
	}

	public MapData(Generator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++)
			put(genK.next(), value);
	}

	public MapData(Iterator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++)
			put(genK.next(), genV.next());
	}

	public MapData(Iterator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++)
			put(genK.next(), value);
	}
}
