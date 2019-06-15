package enumeration.multiDistribution.byarray;

import enumeration.multiDistribution.Competitor;
import enumeration.multiDistribution.Outcome;
import enumeration.multiDistribution.RoShamBo;

public enum RoShamBo5 implements Competitor<RoShamBo5> {
	SCISSORS, ROCK, PAPER;
	static Outcome[][] table = {
		{ Outcome.DRAW, Outcome.LOSE, Outcome.WIN },
		{ Outcome.WIN, Outcome.DRAW, Outcome.LOSE },
		{ Outcome.LOSE, Outcome.WIN, Outcome.DRAW }
	};
	@Override
	public Outcome compete(RoShamBo5 competitor) {
		return table[this.ordinal()][competitor.ordinal()];
	}
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo5.class, 10);
	}
}
