package designpattern.maoxiaodai.simplefactory;

import designpattern.maoxiaodai.simplefactory.BorrowFactory.BorrowType;


public class Main extends ResourceProvider {
	@Override
	public void handle(String line) {
		Borrow borrow = BorrowFactory.createBorrow(BorrowType.valueOf(line));
		borrow.describe();
	}
	public static void main(String[] args) {
		new Main();
	}
}
