package map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlowMap<K, V> implements Map<K, V> {
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();
	@Override
	public int size() {
		return keys.size();
	}
	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}
	@Override
	public V get(Object key) {
		if (!keys.contains(key))
			return null;
		if (key == null) {
			for (K k : keys)
				if (k == null)
					return values.get(keys.indexOf(null));
		} else {
			for (K k : keys)
				if (k.equals(key))
					return values.get(keys.indexOf(k));
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		if (!keys.contains(key)) {
			keys.add(key);
			values.add(value);
			return null;
		} else {
			for (K k : keys) {
				if (k.equals(key)) {
					int index = keys.indexOf(k);
					V old = values.get(index);
					values.set(index, value);
					return old;
				}
			}
		}
		return null;
	}
	@Override
	public V remove(Object key) {
		if (!keys.contains(key)) {
			return null;
		} else {
			for (K k : keys) {
				if (k.equals(key)) {
					int index = keys.indexOf(k);
					V old = values.get(index);
					keys.remove(index);
					values.remove(index);
					return old;
				}
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		a: for (K key : m.keySet()) {
			V value = m.get(key);
			for (K k : keys) {
				if (key.equals(k)) {
					values.set(keys.indexOf(k), value);
					continue a;
				}
			}
			keys.add(key);
			values.add(value);
		}
	}
	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}
	@Override
	public Set<K> keySet() {
		return new HashSet<K>(keys);
	}
	@Override
	public Collection<V> values() {
		return values;
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K,V>>();
		for (K key : keys)
			set.add(new MapEntry<K, V>(key, values.get(keys.indexOf(key))));
		return set;
	}
	@Override
	public String toString() {
		Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
	}
}
