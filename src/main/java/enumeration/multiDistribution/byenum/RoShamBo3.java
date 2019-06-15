package enumeration.multiDistribution.byenum;

import enumeration.multiDistribution.Competitor;
import enumeration.multiDistribution.Outcome;
import enumeration.multiDistribution.RoShamBo;

public enum RoShamBo3 implements Competitor<RoShamBo3> {
	SCISSORS {
		@Override
		public Outcome compete(RoShamBo3 opponent) {
			return compete(PAPER, opponent);
		}
	},
	ROCK {
		@Override
		public Outcome compete(RoShamBo3 opponent) {
			return compete(SCISSORS, opponent);
		}
	},
	PAPER {
		@Override
		public Outcome compete(RoShamBo3 opponent) {
			return compete(ROCK, opponent);
		}
	};
	public abstract Outcome compete(RoShamBo3 opponent);
	
	public Outcome compete(RoShamBo3 loser, RoShamBo3 opponent) {
		return opponent == this ? Outcome.DRAW : (opponent == loser ? Outcome.WIN : Outcome.LOSE);
	}
	
	public static void main(String[] args) {
		RoShamBo.play(RoShamBo3.class, 20);
	}
}
