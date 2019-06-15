package map;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	private static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	private LinkedList<Map.Entry<K, V>>[] buckets = new LinkedList[SIZE];

	@Override
	public V put(K key, V value) {
		V old = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null)
			buckets[index] = new LinkedList<Map.Entry<K, V>>();
		LinkedList<Map.Entry<K, V>> bucket = buckets[index];
		Map.Entry<K, V> entry = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<Map.Entry<K, V>> it = bucket.listIterator();
		while (it.hasNext()) {
			Map.Entry<K, V> iEntry = it.next();
			if (iEntry.getKey().equals(key)) {
				old = iEntry.getValue();
				it.set(entry);
				found = true;
				break;
			}
		}
		if (!found)
			bucket.add(entry);
		return old;
	}
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null)
			return null;
		for (Map.Entry<K, V> entry : buckets[index])
			if (entry.getKey().equals(key))
				return entry.getValue();
		return null;
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for (LinkedList<Map.Entry<K, V>> bucket : buckets) {
			if (bucket == null)
				continue;
			for (Entry<K, V> entry : bucket)
				set.add(entry);
		}
		return set;
	}
}
