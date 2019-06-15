package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountedString {
	private static List<String> created = new ArrayList<String>();
	private int id;
	private String s;
	public CountedString(String s) {
		this.s = s;
		created.add(s);
		
		for (String str : created)
			if (str.equals(s))
				id++;
	}
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + s.hashCode();
		result = 37 * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CountedString
				&& ((CountedString) obj).id == this.id
				&& ((CountedString) obj).s.equals(this.s);
	}
	@Override
	public String toString() {
		return "String:" + s + " id:" + id + " hashCode:" + hashCode();
	}
	
	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
		CountedString[] cs = new CountedString[3];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);
		}
		
		System.err.println(map);
	}
}
