package generator;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		String[] strs = Generated.array(new RandomGenerator.String(), String.class, 7);
		Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		set.addAll(Arrays.asList(strs));
		System.err.println(set);
	}
}
