package enumeration;

import java.util.Random;

public enum Input {
	NICKEL(5),
	DIME(10),
	QUARTER(25),
	DOLLAR(100),
	TOOTHPASTE(200),
	CHIPS(75),
	SODA(100),
	SOAP(50),
	ABORT_TRANSACTION {
		public int amount() {
			throw new RuntimeException("ABORT.amount()");
		}
	},
	STOP {
		public int amount() {
			throw new RuntimeException("SHUT_DOWN.amount()");
		}
	};
	private int value;
	private static Random random = new Random(47);

	private Input(int value) {
		this.value = value;
	}

	private Input() {
	}

	public int amount() {
		return value;
	}
	
	public static Input randomSelection() {
		return values()[random.nextInt(values().length)];
	}
}
