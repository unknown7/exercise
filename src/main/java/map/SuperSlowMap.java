package map;

import java.util.Arrays;

public class SuperSlowMap<K, V> {
	private Object[][] map;
	private int index;
	public SuperSlowMap() {
		map = new Object[0][2];
	}
	public SuperSlowMap(int capacity) {
		map = new Object[capacity][2];
	}
	public V put(K key, V value) {
		ensureCapacity(index + 1);
		
		for (Object[] o : map) {
			if (key.equals(o[0])) {
				@SuppressWarnings("unchecked")
				V old = (V) o[1];
				o[1] = value;
				return old;
			}
		}
		map[index][0] = key;
		map[index][1] = value;
		index++;
		return null;
	}
	@SuppressWarnings("unchecked")
	public V get(K key) {
		for (Object[] o : map)
			if (o[0].equals(key))
				return (V) o[1];
		return null;
	}
	public boolean isEmpty() {
		return map == null || map.length == 0;
	}
	private void ensureCapacity(int size) {
		int length = map.length;
		int oldLength = length;
		if (length < size)
			length = (length * 3) / 2 + 1;
		if (length < size)
			length = size;
		
		Object[][] newMap = Arrays.copyOf(map, length);
		for (int i = oldLength; i < newMap.length; i++)
			newMap[i] = new Object[2];
		map = newMap;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		if (isEmpty())
			return "{}";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < map.length; i++) {
        	Object[] o = map[i];
            K key = (K) o[0];
            V value = (V) o[1];
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (i == map.length - 1)
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
        return "{}";
	}
}
