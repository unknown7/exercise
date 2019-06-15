package enumeration.multiDistribution.test;

public class RoShamBo {
	public static <T extends Competitor<T>> void match(T a, T b) {
		System.err.println(a + " vs " + b + " : " + a.compete(b));
	}
	public static <T extends Enum<T> & Competitor<T>> void play(Class<T> ec, int size) {
		for (int i = 0; i < size; i++)
			match(Enums.random(ec), Enums.random(ec));
	}
}
