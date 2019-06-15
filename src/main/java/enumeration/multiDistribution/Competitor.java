package enumeration.multiDistribution;

public interface Competitor<T extends Competitor<T>> {
	Outcome compete(T competitor);
}
