package designpattern.maoxiaodai.simplefactory;

public class BorrowFactory {
	enum BorrowType {
		fixed, rookie, checking, exit
	}
	public static Borrow createBorrow(BorrowType type) {
		Borrow borrow = null;
		switch (type) {
		case fixed:
			borrow = new Fixed();
			break;
		case rookie:
			borrow = new Rookie();
			break;
		case checking:
			borrow = new Checking();
			break;
		case exit:
			System.exit(0);
		default:
			break;
		}
		return borrow;
	}
}
