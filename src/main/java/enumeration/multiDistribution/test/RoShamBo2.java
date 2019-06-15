package enumeration.multiDistribution.test;

import static enumeration.multiDistribution.test.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
	SCISSORS(DRAW, LOSE, WIN), ROCK(WIN, DRAW, LOSE), PAPER(LOSE, WIN, DRAW);
	private RoShamBo2(Outcome scissors, Outcome rock, Outcome paper) {
		this.vSCISSORS = scissors;
		this.vROCK = rock;
		this.vPAPER = paper;
	}

	private Outcome vSCISSORS, vROCK, vPAPER;

	@Override
	public Outcome compete(RoShamBo2 t) {
		switch (t) {
		default:
		case SCISSORS: return vSCISSORS;
		case ROCK: return vROCK;
		case PAPER: return vPAPER;
		}
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}
