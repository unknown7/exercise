package map;

public class NewHashMap<K, V> {
	private class Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		private int hash;
		public Entry(K key, V value, Entry<K, V> next, int hash) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = hash;
		}
	}
	
	private Entry<K, V>[] table;
	private int size;
	public NewHashMap() {
		this(16);
	}
	@SuppressWarnings("unchecked")
	public NewHashMap(int length) {
		table = new Entry[length];
	}
	public V put(K key, V value) {
		int hash = key.hashCode();
		int index = indexFor(hash);
		for (Entry<K, V> e = table[index]; e != null; e = e.next) {
			if (e.hash == hash && (key == e.key || key.equals(e.key))) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}
		size++;
		addEntry(key, value, hash, index);
		return null;
	}
	private int indexFor(int hash) {
		return hash & (table.length - 1);
	}
	private void addEntry(K key, V value, int hash, int index) {
		Entry<K, V> e = table[index];
		table[index] = new Entry<K, V>(key, value, e, hash);
	}
	
	public V get(K key) {
		int hash = key.hashCode();
		int index = indexFor(hash);
		for (Entry<K, V> e = table[index]; e != null; e = e.next)
			if (e.hash == hash && (key == e.key || key.equals(e.key)))
				return e.value;
		return null;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append('[');
		for (int i = 0; i < table.length; i++) {
			for (Entry<K, V> e = table[i]; e != null; e = e.next) {
				result.append("{key:")
					.append(e.key)
					.append(", value:")
					.append(e.value)
					.append("}");
			}
		}
		result.append(']');
		return result.toString();
	}
	
	public static void main(String[] args) {
		NewHashMap<Integer, String> map = new NewHashMap<Integer, String>();
		for (int i = 0; i < 32; i++)
			map.put(i, String.valueOf(Character.toChars((i + 65))));
		System.err.println(map);
		System.err.println(map.size());
		System.err.println(map.get(5));
	}
}
