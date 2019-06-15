package enumeration.multiDistribution;

import enumeration.Enums;

public class RoShamBo {
	public static <T extends Competitor<T>> void match(T a, T b) {
		System.err.println(a + " vs " + b + " : " + a.compete(b));
	}

	public static <T extends Enum<T> & Competitor<T>> void play(Class<T> ec,
			int num) {
		for (int i = 0; i < num; i++)
			match(Enums.random(ec), Enums.random(ec));
	}
}
