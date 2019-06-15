package enumeration.multiDistribution.byclass;

import java.util.Random;

import enumeration.multiDistribution.Outcome;

public class RoShaBo1 {
	static final int SIZE = 20;
	private static Random rand = new Random(47);
	public static Item newItem() {
		switch (rand.nextInt(3)) {
		default:
		case 0: return new Scissors();
		case 1: return new Paper();
		case 2: return new Rock();
		}
	}
	public static void match(Item a, Item b) {
		System.err.println(a + " vs " + b + " : " + a.compete(b));
	}
	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++)
			match(newItem(), newItem());
	}
}

interface Item {
	Outcome compete(Item it);
	Outcome eval(Paper p);
	Outcome eval(Scissors s);
	Outcome eval(Rock r);
}

class Paper implements Item {
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}
	@Override
	public Outcome eval(Paper p) {
		return Outcome.DRAW;
	}
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.WIN;
	}
	@Override
	public Outcome eval(Rock r) {
		return Outcome.LOSE;
	}
	@Override
	public String toString() {
		return "Paper";
	}
}
class Scissors implements Item {
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}
	@Override
	public Outcome eval(Paper p) {
		return Outcome.LOSE;
	}
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.DRAW;
	}
	@Override
	public Outcome eval(Rock r) {
		return Outcome.WIN;
	}
	@Override
	public String toString() {
		return "Scissors";
	}
}
class Rock implements Item {
	@Override
	public Outcome compete(Item it) {
		return it.eval(this);
	}
	@Override
	public Outcome eval(Paper p) {
		return Outcome.WIN;
	}
	@Override
	public Outcome eval(Scissors s) {
		return Outcome.LOSE;
	}
	@Override
	public Outcome eval(Rock r) {
		return Outcome.DRAW;
	}
	@Override
	public String toString() {
		return "Rock";
	}
}