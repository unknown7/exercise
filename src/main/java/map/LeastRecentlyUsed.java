package map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeastRecentlyUsed {
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>(6, DEFAULT_LOAD_FACTOR, true);
		map.putAll(new CountingMapData(6));
		System.err.println(map);
		
		for (int i = 0; i < 3; i++)
			map.get(i);
		System.err.println(map);
		
		map.get(0);
		System.err.println(map);
	}
}
