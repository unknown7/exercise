package enumeration.multiDistribution.byenummap;

import java.util.EnumMap;

import enumeration.multiDistribution.Competitor;
import enumeration.multiDistribution.Outcome;
import enumeration.multiDistribution.RoShamBo;

public enum RoShamBo4 implements Competitor<RoShamBo4> {
	SCISSORS, ROCK, PAPER;
	static EnumMap<RoShamBo4, EnumMap<RoShamBo4, Outcome>> table = new EnumMap<RoShamBo4, EnumMap<RoShamBo4,Outcome>>(RoShamBo4.class);
	static {
		for (RoShamBo4 it : values()) {
			table.put(it, new EnumMap<RoShamBo4, Outcome>(RoShamBo4.class));
		}
		init(SCISSORS, Outcome.DRAW, Outcome.LOSE, Outcome.WIN);
		init(ROCK, Outcome.WIN, Outcome.DRAW, Outcome.LOSE);
		init(PAPER, Outcome.LOSE, Outcome.WIN, Outcome.DRAW);
	}
	static void init(RoShamBo4 it, Outcome vSCISSORS, Outcome vROCK, Outcome vPAPER) {
		EnumMap<RoShamBo4, Outcome> row = table.get(it);
		row.put(SCISSORS, vSCISSORS);
		row.put(ROCK, vROCK);
		row.put(PAPER, vPAPER);
	}
	@Override
	public Outcome compete(RoShamBo4 competitor) {
		return table.get(this).get(competitor);
	}
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo4.class, 20);
	}
}
