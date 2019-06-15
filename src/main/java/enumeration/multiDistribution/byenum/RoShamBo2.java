package enumeration.multiDistribution.byenum;

import static enumeration.multiDistribution.Outcome.*;
import enumeration.multiDistribution.Competitor;
import enumeration.multiDistribution.Outcome;
import enumeration.multiDistribution.RoShamBo;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
	PAPER(DRAW, LOSE, WIN),
	SCISSORS(WIN, DRAW, LOSE),
	ROCK(LOSE, WIN, DRAW);
	private Outcome vPAPER, vSCISSORS, vROCK;
	private RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
		this.vPAPER = paper;
		this.vSCISSORS = scissors;
		this.vROCK = rock;
	}
	@Override
	public Outcome compete(RoShamBo2 competitor) {
		switch (competitor) {
		default:
		case PAPER: return vPAPER;
		case SCISSORS: return vSCISSORS;
		case ROCK: return vROCK;
		}
	}
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}
