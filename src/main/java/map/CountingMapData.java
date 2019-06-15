package map;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountingMapData extends AbstractMap<Integer, String> {
	private int size;
	private static String[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
	public CountingMapData(int size) {
		this.size = size < 0 ? 0 : size;
	}
	private class Entry implements Map.Entry<Integer, String> {
		private int index;
		public Entry(int index) {
			this.index = index;
		}
		@Override
		public Integer getKey() {
			return index;
		}
		@Override
		public String getValue() {
			return chars[index % chars.length] + Integer.toString(index / chars.length);
		}
		@Override
		public String setValue(String value) {
			throw new UnsupportedOperationException();
		}
	}
	class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
		@Override
		public Iterator<Map.Entry<Integer, String>> iterator() {
			return new Iterator<Map.Entry<Integer, String>>() {
				private Entry entry = new Entry(-1);
				@Override
				public boolean hasNext() {
					return entry.index < size - 1;
				}
				@Override
				public Map.Entry<Integer, String> next() {
					entry.index++;
					return entry;
				}
				@Override
				public void remove() {
					
				}
			};
		}
		@Override
		public int size() {
			return size;
		}
	}
	@Override
	public Set<Map.Entry<Integer, String>> entrySet() {
		return new EntrySet();
	}
}
