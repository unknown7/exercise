package map;

import java.util.Map;

public class MapEntry<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;

	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode())
				^ (value == null ? 0 : value.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MapEntry))
			return false;
		@SuppressWarnings("unchecked")
		MapEntry<K, V> me = (MapEntry<K, V>) obj;
		return (key == null ? me.getKey() == null : key.equals(me.getKey())
				&& value == null ? me.getValue() == null : value.equals(me
				.getValue()));
	}
}